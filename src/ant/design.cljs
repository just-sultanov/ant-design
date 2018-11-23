(ns ant.design
  (:refer-clojure :exclude [list])
  (:require [cljsjs.antd]
            [goog.object :as gobj]
            [clojure.string :as str]
            [reagent.core :as reagent]
            [reagent.interop :as interop]
            [reagent.impl.template :as template]))

;;
;; Helpers
;;

(defn- get-path [component-name]
  (str/split component-name #"\."))


(defn- component [component-name]
  (let [path (get-path component-name)]
    (reagent/adapt-react-class
      (apply gobj/getValueByKeys js/antd path))))


(defn- get-component-name [x]
  (interop/$ x :name))


;;
;; Patch for reagent
;;
;; Disable move caret to end on typing.
;; For using :value instead :defaultValue in component props

(set! template/input-component?
      (fn [x]
        (or (= x "input")
            (= x "textarea")
            (= (get-component-name x) "Input")
            (= (get-component-name x) "TextArea"))))


;;
;; Components
;;

(def affix (component "Affix"))
(def alert (component "Alert"))
(def anchor (component "Anchor"))
(def anchor-link (component "Anchor.Link"))
(def auto-complete (component "AutoComplete"))
(def auto-complete-opt-group (component "AutoComplete.OptGroup"))
(def auto-complete-option (component "AutoComplete.Option"))
(def avatar (component "Avatar"))
(def back-top (component "BackTop"))
(def badge (component "Badge"))
(def breadcrumb (component "Breadcrumb"))
(def breadcrumb-item (component "Breadcrumb.Item"))
(def button (component "Button"))
(def button-group (component "Button.Group"))
(def calendar (component "Calendar"))
(def card (component "Card"))
(def card-grid (component "Card.Grid"))
(def card-meta (component "Card.Meta"))
(def carousel (component "Carousel"))
(def cascader (component "Cascader"))
(def checkbox (component "Checkbox"))
(def checkbox-group (component "Checkbox.Group"))
(def col (component "Col"))
(def collapse (component "Collapse"))
(def collapse-panel (component "Collapse.Panel"))
(def date-picker (component "DatePicker"))
(def date-picker-month-picker (component "DatePicker.MonthPicker"))
(def date-picker-range-picker (component "DatePicker.RangePicker"))
(def date-picker-week-picker (component "DatePicker.WeekPicker"))
(def divider (component "Divider"))
(def drawer (component "Drawer"))
(def dropdown (component "Dropdown"))
(def dropdown-button (component "Dropdown.Button"))
(def form (component "Form"))
(def form-item (component "Form.Item"))
(def icon (component "Icon"))
(def input (component "Input"))
(def input-group (component "Input.Group"))
(def input-search (component "Input.Search"))
(def input-text-area (component "Input.TextArea"))
(def input-number (component "InputNumber"))
(def layout (component "Layout"))
(def layout-content (component "Layout.Content"))
(def layout-footer (component "Layout.Footer"))
(def layout-header (component "Layout.Header"))
(def layout-sider (component "Layout.Sider"))
(def list (component "List"))
(def list-item (component "List.Item"))
(def list-item-meta (component "List.Item.Meta"))
(def locale-provider (component "LocaleProvider"))
(def mention (component "Mention"))
(def mention-nav (component "Mention.Nav"))
(def menu (component "Menu"))
(def menu-divider (component "Menu.Divider"))
(def menu-item (component "Menu.Item"))
(def menu-item-group (component "Menu.ItemGroup"))
(def menu-sub-menu (component "Menu.SubMenu"))
(def modal (component "Modal"))
(def pagination (component "Pagination"))
(def popconfirm (component "Popconfirm"))
(def popover (component "Popover"))
(def progress (component "Progress"))
(def radio (component "Radio"))
(def radio-button (component "Radio.Button"))
(def radio-group (component "Radio.Group"))
(def rate (component "Rate"))
(def row (component "Row"))
(def select (component "Select"))
(def select-opt-group (component "Select.OptGroup"))
(def select-option (component "Select.Option"))
(def skeleton (component "Skeleton"))
(def slider (component "Slider"))
(def spin (component "Spin"))
(def steps (component "Steps"))
(def steps-step (component "Steps.Step"))
(def switch (component "Switch"))
(def table (component "Table"))
(def table-column (component "Table.Column"))
(def table-column-group (component "Table.ColumnGroup"))
(def tabs (component "Tabs"))
(def tabs-tab-pane (component "Tabs.TabPane"))
(def tag (component "Tag"))
(def tag-checkable-tag (component "Tag.CheckableTag"))
(def time-picker (component "TimePicker"))
(def timeline (component "Timeline"))
(def timeline-item (component "Timeline.Item"))
(def tooltip (component "Tooltip"))
(def transfer (component "Transfer"))
(def transfer-list (component "Transfer.List"))
(def transfer-operation (component "Transfer.Operation"))
(def transfer-search (component "Transfer.Search"))
(def tree (component "Tree"))
(def tree-directory-tree (component "Tree.DirectoryTree"))
(def tree-select (component "TreeSelect"))
(def tree-select-tree-node (component "TreeSelect.TreeNode"))
(def tree-tree-node (component "Tree.TreeNode"))
(def upload (component "Upload"))
(def upload-dragger (component "Upload.Dragger"))


;;
;; Notifications
;;

(defn notification [type config]
  (let [config (clj->js config)]
    (js-invoke (.. js/antd -notification) type config)))


(defn notification-success [config]
  (notification "success" config))


(defn notification-error [config]
  (notification "error" config))


(defn notification-warning [config]
  (notification "warning" config))


(defn notification-warn [config]
  (notification "warn" config))


(defn notification-open [config]
  (notification "open" config))


(defn notification-close [key]
  (js-invoke (.. js/antd -notification) "close" key))


(defn notification-destroy []
  (js-invoke (.. js/antd -notification) "destroy"))


(defn notification-config [options]
  (notification "config" options))
