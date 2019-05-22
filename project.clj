(defproject jp.studist/ring-accept-language "0.1.1"
  :description "Ring-Accept-language provides a middleware that parses the Accept-language request header and injects the value into the request hash."
  :url "https://github.com/StudistCorporation/ring-accept-language"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :profiles {:dev {:plugins [[jonase/eastwood "0.3.3" :exclusions [org.clojure/clojure]]]}})
