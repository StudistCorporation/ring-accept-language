(ns ring.middleware.accept-language
  (:require [clojure.string :as str]))

(def ^:private re-wildcard #"\*")

(def ^:private re-qvalue #"0(?:\.\d{0,3})?|1(?:\.0{0,3})?")

(def ^:private re-token #"[!#$%&'*\-+.0-9A-Z\^_`a-z\|~]+")

(def ^:private re-value "[!#$%&'*\\-+.0-9A-Z\\^_`a-z\\|~]+|\\\"(\\\\\\\"|[^\\\"])*\\\"")

(def ^:private re-parameter
  (re-pattern (str "\\s*;\\s*" re-token "=" re-value)))

(def ^:private re-accept-params
  (re-pattern (str "\\s*;\\s*" "q=(" re-qvalue ")")))

(def ^:private re-media-range
  (re-pattern (str "(" re-wildcard "|" re-token ")"
                   "((?:" re-parameter ")*?)")))

(def ^:private re-accept-value
  (re-pattern (str "(" re-media-range ")"
                   "(?:(" re-accept-params ")"
                   "(" re-parameter ")*)?")))

(def ^:private re-accept-header
  (re-pattern (str "(?<=^|,)\\s*(" re-media-range ")\\s*(?:,|$)")))

(defn- parse-accept [header-value]
  (let [[_ type _ _ _ _ q] (re-matches re-accept-value header-value)]
    {type (Double/parseDouble (or q "1"))}))

(defn- parse-header [header]
  (map second (re-seq re-accept-header header)))

(defn accept-language-request [request]
  (if-let [header (get-in request [:headers "accept-language"])]
    (let [values (parse-header header)
          accept (into {} (map parse-accept values))]
      (assoc request :accept-language accept))))

(defn wrap-accept-language [handler]
  (comp handler accept-language-request))
