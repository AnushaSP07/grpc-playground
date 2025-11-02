
Optional is been introduced
- in proto if we add optional keyword before field then it will be treated as optional field
- in generated java code it will be represented as java.util.Optional<T>
- this is available from proto3 version 3.15.0
- so we get option to use like hasField() method to check if field is present or not
- and getField() method will return Optional<T> type
- this is useful in cases where we want to distinguish between default value and field not being set

unknown fields handling
- if we want to get unknown fields from received message then we can use getUnknownFields() method
- it will return UnknownFieldSet object which contains all unknown fields