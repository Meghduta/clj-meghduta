(ns clj-meghduta.schema)


(def KB256 (* 256 1024))

(def queue-push-input {:queue   [#(and (string? %) (<= 3 (.length ^String %) 20)) "Queue name must be a string and between 3 to 20 characters"]
                       :message [#(and (string? %) (<= 1 (.length ^String %) KB256)) "Message must be a string and between 1 to 256KB characters"]})

(def queue-pull-input {:queue #(and (string? %) (<= 3 (.length ^String %) 20))})

(defn- validate-schema-key [key schema data]
  (let [[validate-fn message] (key schema)
        data-key (key data)]
    (if (validate-fn data-key)
      true
      {key message})))

(defn validate [schema data]
  (let [schema-keys (keys schema)
        data-keys (keys data)]
    (if (not= schema-keys data-keys)
      {:message "Invalid input"}
      (let [validations (map #(validate-schema-key % schema data) schema-keys)
            failed-validations (filter #(not= % true) validations)]
        (if (empty? failed-validations)
          true
          (apply merge failed-validations))))))

