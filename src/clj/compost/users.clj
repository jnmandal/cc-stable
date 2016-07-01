(ns compost.users
  (require [compost.schema :refer [user]]
           [korma.core :refer [select where insert values]]
           [clj-time.core :refer [now]]
           [clj-time.coerce :refer [to-sql-time]]))

(defn all []
  (select user))

(defn find-by-email [email]
  (select user
          (where {:email email})))

(defn new [user-data]
  (insert user
          (values [(merge {:created_at (to-sql-time (now))
                           :updated_at (to-sql-time (now))}
                           user-data)])))
