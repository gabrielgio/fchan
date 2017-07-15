(ns fchan.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json])
  (:gen-class))

(def ^String fchan-url "https://a.4cdn.org")

(defn get-threadids
  "Get a list of ids of a board"
  [^String board]
  (json/read-str (:body (client/get (str fchan-url "/" board "/threads.json")))
                 :key-fn keyword))

(defn- normilize-thread
  "I will return 0 in case thread-nth overflow page's number of threads,
  otherwise will return current thread-nth"
  [page thread-nth]
  (if (>= thread-nth (count (:threads page)))
    0
    thread-nth))

(defn- normilize-page
  "It will return next page in case thread-nth overflows page's number of threads
  otherwise will return the current"
  [t-ids page thread-nth]
  (if (>= thread-nth (count (:threads page)))
    (first (filter (fn [x] (= (:page x) (inc (:page page)))) (get-threadids "a")))
    page))

(defn get-boards
  "Get boads' information."
  []
  (json/read-str (:body (client/get (str fchan-url "/boards.json")))
                 :key-fn keyword))

(defn get-board-catalog
  "Get catalog of a thread."
  [^String board]
  (json/read-str (:body (client/get (str fchan-url "/" board "/catalog.json"))) :key-fn keyword))

(defn get-board-page
  "Get information of given page of given boad"
  [^String board ^Integer page]
  (json/read-str (:body (client/get (str fchan-url "/" board "/" page ".json")))
                 :key-fn keyword))

(defn get-archive-threads
  "Get list of all archived thread ids"
  [^String board]
  (json/read-str (:body (client/get (str fchan-url "/" board "/archive.json"))) :key-fn keyword))

(defn get-thread
  "Get thread's posts"
  [^String board ^Integer t-number]
  (json/read-str (:body (client/get (str fchan-url "/" board "/thread/" t-number ".json")))
                 :key-fn keyword))

(defn get-threads
  "Get all threads of a board.
  This is a higher level of get-thread and
  it will return a laze-seq of all threads of a board."
  ([^String board] (get-threads board (get-threadids board)))
  ([^String board t-ids] (get-threads board t-ids (first t-ids) 0))
  ([^String board t-ids page ^Integer thread-nth]
   (if (not= page nil)
     (lazy-seq (cons (get-thread board (:no (nth (:threads page) (normilize-thread page thread-nth))))
                     (get-threads board t-ids
                                  (normilize-page t-ids page (inc thread-nth))
                                  (normilize-thread page (inc thread-nth))))))))
