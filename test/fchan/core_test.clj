(ns fchan.core-test
  (:require [clojure.test :refer :all]
            [fchan.core :refer :all]))

(def not-nil? (complement nil?))

(deftest simple-get-thread-ids-test
  (testing "Get thread ids"
    (is (not (empty? (get-thread-ids "a"))))))

(deftest content-get-thread-ids-test
  (testing "Content validation for get thread ids"
    (is (every? #(not-nil? (:page %)) (get-thread-ids "a")))
    (is (every? #(not-nil? (:threads %)) (get-thread-ids "a")))
    (is (every? #(every? (fn [x] (and (not-nil? (:no x)) (not-nil? (:last_modified x))))
                         (:threads %)) (get-thread-ids "a")))))

(deftest simple-get-boards-test
  (testing "Simple get boards test"
    (is (not-nil? (:boards (get-boards))))
    (is (not-nil? (:troll_flags (get-boards))))))

(deftest content-get-boards-test
  (testing "Content validation for get-boards"
    (is (not-nil? (:boards (get-boards))))
    (is (not-nil? (:troll_flags (get-boards))))
    (is (every? (fn [x] (and (not-nil? (:cooldowns x))
                             (not-nil? (:ws_board x))
                             (not-nil? (:max_webm_duration x))
                             (not-nil? (:max_comment_chars x))
                             (not-nil? (:title x))
                             (not-nil? (:pages x))
                             (not-nil? (:meta_description x))
                             (not-nil? (:image_limit x))
                             (not-nil? (:per_page x))
                             (not-nil? (:max_webm_filesize x))
                             (not-nil? (:max_filesize x))
                             (not-nil? (:board x)))) (:boards (get-boards))))
    (is (let [t (:troll_flags (get-boards))]
          (and (not-nil? (:EU t)) (not-nil? (:CM t))
               (not-nil? (:PR t)) (not-nil? (:UN t))
               (not-nil? (:PC t)) (not-nil? (:MF t))
               (not-nil? (:BL t)) (not-nil? (:KN t))
               (not-nil? (:CF t)) (not-nil? (:AN t))
               (not-nil? (:AC t)) (not-nil? (:WP t))
               (not-nil? (:RE t)) (not-nil? (:TR t))
               (not-nil? (:TM t)) (not-nil? (:NB t))
               (not-nil? (:JH t)) (not-nil? (:GY t))
               (not-nil? (:FC t)) (not-nil? (:DM t))
               (not-nil? (:GN t)) (not-nil? (:NZ t)))))))

(deftest get-archive-threads-test
  (testing "Test get archived thread ids"
    (is (let [ids (get-archive-threads "a")] (every? integer? ids)))))

(deftest get-board-catalog-test
  (testing "Test for 404"
    (is (count (get-board-catalog "gif")))))

(deftest get-board-page-test
  (testing "Test for 404"
    (is (not-nil? (:threads (get-board-page "a" 1))))))

(deftest get-thread-test
  (testing "Test for 404"
    (is (not-nil? (get-thread "gif" 6226033)))))

(deftest get-image-url-test
  (testing "Test for get-image-url"
    (is (= "https://i.4cdn.org/co/1518482846189.jpg" (get-image-url "co" 1518482846189 ".jpg")))))

(deftest get-thumbnail-url-test
  (testing "Test for get-thumbnail-url"
    (is (= "https://i.4cdn.org/co/1518482846189s.jpg" (get-thumbnail-url "co" 1518482846189)))))