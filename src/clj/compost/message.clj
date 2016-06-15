(ns compost.message)

(defentity message
  (belongs-to from-user)
  (belongs-to to-user)
  (belongs-to thread)
  (has-many replies))
