# Kodic

## About
Kotlin meets Codic
This is sample of Kotlin app about using Codic API.
[Codic API References](https://codic.jp/docs/api)

## Pattern
This project is MVC Pattern

### Component
Component is user view (activity / fragment / adapter / widget)
#### Relation
##### Activity and Fragment
activities are extends BaseActivity
fragments are extends BaseFragment
##### Activity and Fragment relation
- MainActivity 1 - 1 Fragment
- DetailsActivity 1 - n Fragment

### Store
Store is component-data connector.
If component will access to data, request this.
These store are provides from StoreModule.
#### Codic
codic is Codic api connection rest client.
These store are inject service(codic -> service is retrofit services) from ApiModule.
#### Realm
realm is realm database connection client.
This store is inject realm.

### Data
Data is model classes.
These folder are codic and realm and... service name folder.

### Util
util is app scope using utility.

## Rule


## Using Library
### Database
- realm
## DI (Dependency Injection)
- dagger
### Event
- otto
### Font
- calligraphy
### Image
- glide
- glide transformation
### Kotlin
- kotlin
- kotterknife
### Logger
- timber
### Reactive
- rxjava
- rxt4a
- rxbinding
- rxbinding-support-v4
- rxbinding-appcompat-v7
- rxbinding-recyclerview-v7
- rxbinding-kotlin
- rxbinding-support-v4-kotlin
- rxbinding-appcompat-v7-kotlin
- rxbinding-recyclerview-v7-kotlin
### Rest Client
- retrofit
- okhttp-urlconnection
- okhttp
### Support Library
- support-v4
- appcompat-v7
- cardview-v7
- recyclerview-v7
- design-support-lib