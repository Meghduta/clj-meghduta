(ns clj-meghduta.util-test
  (:require [clojure.test :refer :all]
            [clj-meghduta.util :refer :all]
            [clojure.java.io :as io]))


(deftest test-util

  (testing "Map to JSON"
    (is (= (map-to-json {"message" "I am from Pluto"}) "{\"message\":\"I am from Pluto\"}")))

  (testing "JSON Stream to Map conversion"
    (is (=
          {"message" "Andromeda"}
          (stream-to-map (io/input-stream (.getBytes "{\"message\": \"Andromeda\" }")))))))