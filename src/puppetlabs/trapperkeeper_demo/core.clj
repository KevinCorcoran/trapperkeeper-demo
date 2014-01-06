(ns puppetlabs.trapperkeeper-demo.core
  (:require [puppetlabs.trapperkeeper.core :refer [defservice]]
            [puppetlabs.trapperkeeper-demo.utils :refer :all]))

(defservice hello-world-service
  {:depends []
   :provides []}
  (log-periodically "Hello, world!")
  {})

(defservice moo-service
  {:depends []
   :provides []}
  (log-periodically "MOOO")
  {})
