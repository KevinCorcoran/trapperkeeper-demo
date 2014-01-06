(ns puppetlabs.trapperkeeper-demo
  (:import (java.util.concurrent Executors TimeUnit))
  (:require [clojure.tools.logging :as log]
            [puppetlabs.trapperkeeper.core :refer [defservice]]))

(def task
  (proxy [Runnable] []
    (run []
      (println "I AM A THING"))))

(defservice hello-service
  {:depends []
   :provides []}
  (-> (Executors/newScheduledThreadPool 1)
      (.scheduleAtFixedRate task 0 2 (TimeUnit/SECONDS)))
  {})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def interesting-task
  (proxy [Runnable] []
    (run []
      (log/info "HERE IS SOMETHING INTERESTING"))))

(defservice data-service
            {:depends []
             :provides [get-interesting-data]}
            {:get-interesting-data
              (fn [] "MOOO")})

(defservice interesting-service
  {:depends [[:data-service get-interesting-data]]
   :provides []}
  (-> (Executors/newScheduledThreadPool 1)
      (.scheduleAtFixedRate interesting-task 0 2 (TimeUnit/SECONDS)))
  {})
