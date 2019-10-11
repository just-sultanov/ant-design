[![Clojars Project](https://img.shields.io/clojars/v/ant-design/ant.design.svg)](https://clojars.org/ant-design/ant.design)
[![GitHub License](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE)

# ant.design

A ClojureScript library for [Ant Design](https://ant.design)


#### Quick Start Guide

Add the following dependency in your project:

```clojure
;; project.clj or build.boot
[ant.design/ant.design "RELEASE"]

;; deps.edn
{:deps {ant.design/ant.design {:mvn/version "RELEASE"}}}
```



#### Usage

```clojure
(ns example.app
  (:require [ant.design :as ant]))

(defn my-button []
  [ant/button {:onClick #(js/console.log "Amazing...")}
    "Click me"])
```



#### Deploy

```bash
# create a new git tag (available types `minor`, `major`)
$ make patch

# push a new git tag to Github then wait for GitHub Actions
# start to deploy the new version to clojars
$ git push origin --tags  
```



#### Changelog


##### v5.0.0
- added automated releases with Github Actions
- changed artifact coordinates

##### v4.0.0
- the library version is now independent of the `antd` library version
- added new libraries for automation
    - com.workframe/garamond 0.4.0
    - deps-deploy 0.0.9
- cambada library updated to 1.0.7
- deprecated components:
    - `mention`
    - `mention-nav`
    - `locale-provider`
- added form functions:
    - `get-field-decorator`
    - `get-field-error`
    - `get-fields-error`
    - `get-field-value`
    - `get-fields-value`
    - `field-touched?`
    - `fields-touched?`
    - `field-validating?`
    - `reset-fields`
    - `set-fields`
    - `set-fields-value`
    - `validate-fields`
    - `validate-fields-and-scroll`

##### v3.22.0-1
- deps are explicitly indicated

##### v3.22.0-0
- antd library updated to [v3.22.0][antd-v3.22.0]

##### v3.21.1-0
- antd library updated to [v3.21.1][antd-v3.21.1]
- added new component `breadcrumb-separator`

##### v3.19.0-0
- antd library updated to [v3.19.0][antd-v3.19.0]
- added new components `mentions`, `descriptions`



#### License

[Copyright © 2019 Ilshat Sultanov](LICENSE)



[antd-v3.22.0]: https://ant.design/changelog#3.22.0
[antd-v3.21.1]: https://ant.design/changelog#3.21.1
[antd-v3.19.0]: https://ant.design/changelog#3.19.0
