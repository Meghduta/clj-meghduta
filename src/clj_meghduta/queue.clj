(ns clj-meghduta.queue
  (:require [clj-meghduta.util :as util]
            [clj-meghduta.schema :as schema])
  (:import (java.util.concurrent ConcurrentLinkedQueue)))


(def queue-map (transient {}))


(defn- push [input]
  (let [queue-name (input "queue")
        message (input "message")]
    (when (not (contains? queue-map queue-name))
      (assoc! queue-map queue-name (new ConcurrentLinkedQueue)))
    (.offer ^java.util.Queue (queue-map queue-name) message)
    {:body "Message Queued"}))

(defn validate-and-push [req]
  (let [input (util/stream-to-map (:body req))
        validation-result (schema/validate schema/queue-push-input input)]
    (if validation-result
      (push input)
      validation-result)))


(defn- pull [input]
  (let [queue-name (input "queue")
        queue (queue-map queue-name)
        message (and queue (.poll ^java.util.Queue (queue-map queue-name)))]
    {:body (util/map-to-json {"message" message})}))

(defn validate-and-pull [req]
  (let [input (util/stream-to-map (:body req))
        validation-result (schema/validate schema/queue-pull-input input)]
    (if validation-result
      (pull input)
      validation-result)))
