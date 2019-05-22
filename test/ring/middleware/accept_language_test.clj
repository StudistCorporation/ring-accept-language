(ns ring.middleware.accept-language-test
  (:require [clojure.test :refer :all]
            [ring.middleware.accept-language :as al]))
(def mock-request
  {:headers
    {"host" "localhost:3000",
     "user-agent" "Mozilla/5.0",
     "connection" "keep-alive",
     "accept" "text/html,application/xhtml+xml,application/xml;q=0.9",
     "accept-language" "ja,en-US;q=0.9,en;q=0.8",
     "accept-encoding" "gzip, deflate, br",
     "cache-control" "max-age=0"}})

(deftest a-test
  (testing "Multi accept-language"
    (let [result (:accept-language (al/accept-language-request mock-request))]
      (is (= result {"ja" 1.0, "en-US" 0.9, "en" 0.8}))))
  (testing "Single accept-language"
    (let [result (:accept-language (al/accept-language-request
                                     (assoc-in mock-request
                                               [:headers "accept-language"]
                                               "ja")))]
      (is (= result {"ja" 1.0}))))
  (testing "No accept-language"
    (let [request {:headers {"host" "localhost"}}
          returned-request (al/accept-language-request request)
          result (:accept-language returned-request)]
      (is (= request returned-request))
      (is (= result nil))))
  (testing "No requests"
    (let [result (:accept-language (al/accept-language-request {}))]
      (is (= result nil)))))
