(ns clj-meghduta.handler-test
  (:require [clojure.test :refer :all]
            [clj-meghduta.handler :refer :all]
            [clojure.java.io :as io]))

(deftest test-queue

  (testing "Invalid Condition"
    (let [push-response (handle-request {:request-method :post
                                         :uri            "/meghduta/jakarta/push"
                                         :body           (io/input-stream (.getBytes "{\"message\": \"Andromeda\", \"queue\" : \"galaxy\" }"))})]
      (is (= push-response {:status  404
                            :body    "Invalid API or command"}))))

  (testing "Queue Push and Pull - Success Condition"
    (let [push-response (handle-request {:request-method :post
                                         :uri            "/meghduta/queue/push"
                                         :body           (io/input-stream (.getBytes "{\"message\": \"Andromeda\", \"queue\" : \"galaxy\" }"))})
          pull-response (handle-request {:request-method :post
                                         :uri            "/meghduta/queue/pull"
                                         :body           (io/input-stream (.getBytes "{\"queue\": \"galaxy\" }"))})]
      (is (= push-response {:body "Message Queued"}))
      (is (= pull-response {:body "{\"message\":\"Andromeda\"}"})))))
