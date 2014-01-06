(ns puppetlabs.trapperkeeper-demo.utils
  (:import (java.util.concurrent Executors TimeUnit))
  (:require [clojure.tools.logging :as log]))

(defn log-periodically
  [msg interval]
  (-> (Executors/newScheduledThreadPool 1)
      (.scheduleAtFixedRate (proxy [Runnable] []
                              (run []
                                (log/info msg))) 0 interval (TimeUnit/SECONDS))))
