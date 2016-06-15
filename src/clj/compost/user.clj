(ns compost.user
  (:require [compost.questionnaire :as questionnaire]))

(use 'korma.core)

(declare users questionnaire messages)

(defentity users
  (pk :id)
  (table :users)
  (entity-fields :name :email :type) 
  (has-one questionnaire))

(defentity residents
  (table (subselect users
            (where {:type 0})) :residents))

(defentity composters
  (table (subselect users
            (where {:type 1})) :composters))

(defentity farmers
  (table (subselect users
            (where {:type 2})) :farmers))
