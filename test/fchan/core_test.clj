(ns fchan.core-test
  (:require [clojure.test :refer :all]
            [fchan.core :refer :all]))

(deftest a-test
  (testing "Get thread ids"
    (is (get 0 1))))
