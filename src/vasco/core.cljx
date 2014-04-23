(ns vasco.core
  (:require [clojure.string :as string]))

;; eg. Combines "/users/:username" with {:username "john"} to get "/users/john".a
(defn- render
  [template data]
  (letfn [(f [acc [k v]] (string/replace acc (str ":" (name k)) (str v)))]
    (reduce f template data)))

(defn make-path-fn [template]
  (fn [& data]
    (let [m (into {} (vec (map vec (partition 2 data))))]
      (render template m))))

#+clj
(defmacro defendpoint [name-sym path]
  (let [name (name name-sym)
        x-template-sym (symbol (str name "-template"))
        x-path-sym (symbol (str name "-path"))]
    `(do (def ~x-template-sym ~path)
         (def ~x-path-sym (make-path-fn ~x-template-sym)))))
