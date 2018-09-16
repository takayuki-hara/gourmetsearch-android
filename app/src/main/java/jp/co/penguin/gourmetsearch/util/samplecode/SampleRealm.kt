package jp.co.penguin.gourmetsearch.util.samplecode

import android.content.Context
import android.util.Log
import io.realm.*
import io.realm.annotations.PrimaryKey
import io.realm.exceptions.RealmException


open class Dog : RealmObject() {
    var name: String? = null
    var age: Int = 0
}

open class Person : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var name: String? = null
    var dogs: RealmList<Dog>? = null // Declare one-to-many relationships
}

class SampleRealm {
    fun sample(context: Context) {
        // Use them like regular java objects
        val dog = Dog()
        dog.name = "Rex"
        dog.age = 1

        // Initialize Realm (just once per application)
        Realm.init(context)

        // Get a Realm instance for this thread
        val realm = Realm.getDefaultInstance()
        try {
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        } catch (e: RealmException) {
            Log.e("RealmException", e.toString())
        }

        // Query Realm for all dogs younger than 2 years old
        val puppies = realm.where(Dog::class.java).lessThan("age", 2).findAll()
        puppies.size // => 0 because no dogs have been added to the Realm yet

        // Persist your data in a transaction
        realm.beginTransaction()
        val managedDog = realm.copyToRealm(dog) // Persist unmanaged objects
        val person = realm.createObject(Person::class.java, 1) // Create managed objects directly
        person.dogs?.add(managedDog)
        realm.commitTransaction()


        // Listeners will be notified when data changes
        puppies.addChangeListener(OrderedRealmCollectionChangeListener<RealmResults<Dog>> { results, changeSet ->
            // Query results are updated in real time with fine grained notifications.
            changeSet.insertions // => [0] is added.
        })

        // Asynchronously update objects on a background thread
        realm.executeTransactionAsync(object : Realm.Transaction {
            override fun execute(bgRealm: Realm) {
                val dog = bgRealm.where(Dog::class.java).equalTo("age", 1 as Int).findFirst()
                dog?.age ?: 3
            }
        }, object : Realm.Transaction.OnSuccess {
            override fun onSuccess() {
                // Original queries and Realm objects are automatically updated.
                puppies.size // => 0 because there are no more puppies younger than 2 years old
                managedDog.age   // => 3 the dogs age is updated
            }
        })
    }
}
