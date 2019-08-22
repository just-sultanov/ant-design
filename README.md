[![Clojars Project](https://img.shields.io/clojars/v/ant.design.svg)](https://clojars.org/ant.design)
[![GitHub License](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/just-sultanov/ant-design/blob/master/LICENSE)

# ant.design

A ClojureScript library for [Ant Design](https://ant.design)


#### Quick Start Guide

Add the following project dependency:

```clojure
;; project.clj or build.boot
[ant.design "3.22.0-1"]

;; deps.edn
{:deps {ant.design {:mvn/version "3.22.0-1"}}}
```



#### Usage

```clojure
(ns example.app
  (:require [ant.design :as ant]))

(defn my-button []
  [ant/button {:onClick #(js/console.log "Amazing...")}
    "Click me"])
```



#### Changelog

##### v3.22.0-1
- deps are explicitly indicated

##### v3.22.0-0
- antd library updated to [v3.22.0][v3.22.0]

##### v3.21.1-0
- antd library updated to [v3.21.1][v3.21.1]
- added new component `breadcrumb-separator`

##### v3.19.0-0
- antd library updated to [v3.19.0][v3.19.0]
- added new components `mentions`, `descriptions`



#### License

Copyright © 2019 Ilshat Sultanov



[v3.22.0]: https://ant.design/changelog#3.22.0
[v3.21.1]: https://ant.design/changelog#3.21.1
[v3.19.0]: https://ant.design/changelog#3.19.0
