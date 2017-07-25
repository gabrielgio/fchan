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
