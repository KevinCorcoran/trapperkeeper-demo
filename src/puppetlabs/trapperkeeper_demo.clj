(ns puppetlabs.trapperkeeper-demo
  (:import (java.util.concurrent Executors TimeUnit))
  (:require [puppetlabs.trapperkeeper.core :refer [defservice]]))

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
