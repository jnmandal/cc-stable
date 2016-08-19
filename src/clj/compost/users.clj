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

;; {
;;   "name": "tester mc test",
;;   "email": "test1@test.com",
;;   "password": "test123",
;;   "password_confirmation": "test123",
;;   "participant_type": "resident",
;;   "questionnaire": []
;; }

(defn password-present? [user-data]
  (println (:password user-data))
  (println (:password_confirmation user-data)))

(defn new [user-data]
  (password-present? user-data)
  (insert user
          (values [(merge {:created_at (to-sql-time (now))
                           :updated_at (to-sql-time (now))}
                          user-data)])))
