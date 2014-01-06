(ns puppetlabs.trapperkeeper-demo.core
  (:require [puppetlabs.trapperkeeper.core :refer [defservice]]
            [puppetlabs.trapperkeeper-demo.utils :refer [log-periodically]]))

(defservice hello-world-service
  {:depends []
   :provides []}
  (log-periodically "Hello, world!" 2)
  {})

(defservice animal-service
  {:depends [[:config-service get-in-config]]
   :provides []}
  (let [msg       (get-in-config [:animals :sound] "MOOOO, I am a cow.")
        interval  (get-in-config [:animals :interval] 2)]
    (log-periodically msg interval))
  {})
