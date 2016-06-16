(ns compost.schema)

(use 'korma.db)
(use 'korma.core)

(defdb db (postgres {:db "compost-crossing-grape_development"
                     :host "localhost"
                     :port "5432"}))

(declare user questionnaire message)

(defentity user
  (pk :id)
  (table :users)
  (has-one questionnaire {:fk :user_id})
  (entity-fields :name
                 :email
                 :participant_type
                 :created_at
                 :updated_at
                 ))

(defentity resident
  (table (subselect user
            (where {:participant_type 0})) :residents))

(defentity composter
  (table (subselect user
            (where {:participant_type 1})) :composters))

(defentity farmer
  (table (subselect user
            (where {:participant_type 2})) :farmers))

(defentity questionnaire
  (table :questionnaires)
  (belongs-to user)
  (entity-fields :fields
                 :created_at
                 :updated_at
                 ))

(defentity message
  (table :messages)
  (belongs-to user)
  (entity-fields :to_user_id
                 :from_user_id
                 :thread_id
                 :content
                 ))
