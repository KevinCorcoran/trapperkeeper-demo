(ns puppetlabs.trapperkeeper-demo.core
  (:require [puppetlabs.trapperkeeper.core :refer [defservice]]
            [puppetlabs.trapperkeeper-demo.utils :refer :all]))

(defservice hello-world-service
  {:depends []
   :provides []}
  (log-periodically "Hello, world!")
  {})

(defservice animal-service
  {:depends [[:config-service get-in-config]]
   :provides []}
  (-> (get-in-config [:animal-service :sound] "MOOOO, I am a cow.")
      (log-periodically))
  {})
