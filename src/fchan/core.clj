(ns fchan.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json])
  (:gen-class))

(def ^String fchan-url "https://a.4cdn.org")

(def spoiler-url "https://s.4cdn.org/image/spoiler.png")

(def sticky-url "https://s.4cdn.org/image/sticky.gif")

(def admin-url "https://s.4cdn.org/image/adminicon.gif")

(def mod-url "https://s.4cdn.org/image/modicon.gif")

(def manager-url "https://s.4cdn.org/image/managericon.gif")

(def developer-url "https://s.4cdn.org/image/developericon.gif")

(def founder-url "https://s.4cdn.org/image/foundericon.gif")

(def spoiler-url "https://s.4cdn.org/image/spoiler.png")

(def op-deleted-url "https://s.4cdn.org/image/filedeleted.gif")

(def delete-url "https://s.4cdn.org/image/filedeleted-res.gif")

(defn get-thread-ids
  "Get a list of ids of a board"
  [^String board]
  (json/read-str (:body (client/get (str fchan-url "/" board "/threads.json")))
                 :key-fn keyword))

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
  "Get information of given page of given board"
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

(defn get-image-url
  "Return a image url string"
  [^String board ^Integer tim ^String ext]
  (str "https://i.4cdn.org/" board "/" tim ext))

(defn get-thumbnail-url
  "Return a thumbnail image url string"
  [^String board ^Integer tim]
  (str "https://i.4cdn.org/" board "/" tim "s.jpg"))