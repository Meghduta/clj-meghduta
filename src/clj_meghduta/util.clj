(ns clj-meghduta.util
    (:require [cheshire.core :as cheshire])
    (:import (java.io InputStreamReader BufferedReader)))

(defn stream-to-json [stream] 
    (-> (new BufferedReader (new InputStreamReader stream))
    (cheshire/parse-stream)))

(defn map-to-json [map]
    (cheshire/generate-string map))