(ns ant.design.forms
  "Form is used to collect, validate, and submit the user input, usually contains
  various form items including checkbox, radio, input, select, and etc.

  When to use:
    - When you need to create a instance or collect information.
    - When you need to validate fields in certain rules."
  (:require
    [reagent.core :as r]
    [goog.object :as gobj]))

(def enhancer
  (gobj/getValueByKeys js/antd "Form" "create"))


(defn invoke-form-method
  "Invoke form method by the given method name."
  [f args]
  (->> args
       (clj->js)
       (apply f)
       (js->clj :keywordize-keys true)))


(defn create
  "Creates a new form."
  [params form]
  (let [{:keys [options props]
         :or   {options {}
                props   {}}} params]
    (r/create-element
      ((enhancer (clj->js options))
       (r/reactify-component form))
      (clj->js props))))


(defn current-form
  "Returns the current form."
  []
  (-> (r/current-component)
      (r/props)
      (js->clj :keywordize-keys true)
      :form))


(defn decorate-field
  "Decorates the field by the given params."
  [params field]
  (let [{:keys [id form options]
         :or   {options {}}} params
        decorator    (:getFieldDecorator form)
        decorator-fn (decorator id (clj->js options))]
    (decorator-fn (r/as-element field))))


(defn get-field-decorator
  "Two-way binding for form, please read below for details.

  After wrapped by getFieldDecorator, value (or other property defined by valuePropName)
  onChange (or other property defined by trigger) props will be added to form controls, the
  flow of form data will be handled by Form which will cause:

  - You shouldn't use onChange to collect data, but you still can listen to onChange (and so on) events.
  - You can't set value of form control via value defaultValue prop, and you should set default value with initialValue
  in getFieldDecorator instead.
  - You shouldn't call setState manually, please use this.props.form.setFieldsValue to change value programmatically."
  [form & args]
  (invoke-form-method (:getFieldDecorator form) args))


(defn get-field-error
  "Get the error of a field."
  [form & args]
  (invoke-form-method (:getFieldError form) args))


(defn get-fields-error
  "Get the specified fields' error. If you don't specify a parameter,
  you will get all fields' error."
  [form & args]
  (invoke-form-method (:getFieldsError form) args))


(defn get-field-value
  "Get the value of a field."
  [form & args]
  (invoke-form-method (:getFieldValue form) args))


(defn get-fields-value
  "Get the specified fields' values. If you don't specify a parameter,
  you will get all fields' values."
  [form & args]
  (invoke-form-method (:getFieldsValue form) args))


(defn field-touched?
  "Check whether a field is touched by getFieldDecorator's options.trigger event."
  [form & args]
  (boolean (invoke-form-method (:isFieldTouched form) args)))


(defn fields-touched?
  "Check whether any of fields is touched by getFieldDecorator's options.trigger
  event."
  [form & args]
  (boolean (invoke-form-method (:isFieldsTouched form) args)))


(defn field-validating?
  "Check if the specified field is being validated."
  [form & args]
  (boolean (invoke-form-method (:isFieldValidating form) args)))


(defn reset-fields
  "Reset the specified fields' value (to initialValue) and status. If you don't
  specify a parameter, all the fields will be reset."
  [form & args]
  (invoke-form-method (:resetFields form) args))


(defn set-fields
  "Set value and error state of fields."
  [form & args]
  (invoke-form-method (:setFields form) args))


(defn set-fields-value
  "Set the value of a field.

  (Note: please don't use it in componentWillReceiveProps, otherwise,
  it will cause an endless loop, reason)."
  [form & args]
  (invoke-form-method (:setFieldsValue form) args))


(defn validate-fields
  "Validate the specified fields and get their values and errors. If you don't
  specify the parameter of fieldNames, you will validate all fields."
  [form & args]
  (invoke-form-method (:validateFields form) args))


(defn validate-fields-and-scroll
  "This function is similar to validateFields, but after validation,
  if the target field is not in visible area of form, form will be automatically
  scrolled to the target field area."
  [form & args]
  (invoke-form-method (:validateFieldsAndScroll form) args))
