(ns clj-meghduta.schema-test
  (:require [clojure.test :refer :all]
            [clj-meghduta.schema :refer :all]))


(deftest test-util-functions

  (testing "Validate success condition"
    (is
      (validate queue-push-input {:queue "Queue Name 1" :message "Capitalism is Individualism"})))

  (testing "Validate failure condition - Invalid data type"
    (is (=
          {:queue "Queue name must be a string and between 3 to 20 characters"}
          (validate queue-push-input {:queue 1 :message "Capitalism is Individualism"}))))

  (testing "Validate failure condition - Overflowing data value"
    (is (=
          {:queue "Queue name must be a string and between 3 to 20 characters"
           :message "Message must be a string and between 1 to 256KB characters"}
          (validate queue-push-input {:queue "q" :message ""}))))

  (testing "Validate failure condition - Insufficient keys"
    (is (=
          {:message "Invalid input"}
          (validate queue-push-input {:ola 1 :message "Capitalism is Individualism"})))))