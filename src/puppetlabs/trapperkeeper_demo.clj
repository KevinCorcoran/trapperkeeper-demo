(ns puppetlabs.trapperkeeper-demo
  (:import (java.util.concurrent Executors TimeUnit))
  (:require [clojure.tools.logging :as log]
            [puppetlabs.trapperkeeper.core :refer [defservice]]))

(defn log-periodically
  [msg]
  (-> (Executors/newScheduledThreadPool 1)
      (.scheduleAtFixedRate (proxy [Runnable] []
                              (run []
                                (log/info msg))) 0 2 (TimeUnit/SECONDS))))

(defservice hello-service
  {:depends []
   :provides []}
  (log-periodically "Hello")
  {})

(defservice interesting-service
  {:depends []
   :provides []}
  (log-periodically "SOMETHING INTERESTING MOOOOO")
  {})
