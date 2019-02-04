[![Clojars Project](https://img.shields.io/clojars/v/ant.design.svg)](https://clojars.org/ant.design)
[![GitHub License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/just-sultanov/ant-design/blob/master/LICENSE)

# ant.design

#### Quick Start Guide

Add the following project dependency:

```clojure
;; project.clj or build.boot
[ant.design "3.13.1-0"]

;; deps.edn
{:deps {ant.design {:mvn/version "3.13.1-0"}}}
```


#### Notes:

##### Fix caret position with none, whitespace and simple compile optimizations

```clojure
(ns app
  (:require [ant.design :as ant]))
    
(defn ^:export init []
  (ant/fix-caret-position!)
  (mount-root))
    
```


##### Fix caret position with advanced compile optimization

Advanced compilation loses component names, so the suggestion above doesn't work. You can use the next solution:

```clojure
(ns app
  (:require [ant.design :as ant]))

(defn- text-input []
  "It's a dirty hack to force re-render input fields (input, text-area) with the default value"
  (let [*key   (atom 0)
        *value (atom "")]
    (fn []
      ^{:key @*key}
      [ant/input-text-area {:defaultValue @*value
                            :onChange     (fn [e]
                                            (reset! *value (.. e -target -value))
                                            (swap! *key inc))}])))

```

#### License

Copyright © 2018 Ilshat Sultanov
