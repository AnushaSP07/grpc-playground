
In Grpc
- if we have 5 fields and out of 5, if we have commentted any 2 fields 
- it will not show in output as null. it will not at all consider it and not at all 
- use that value


One-of
- if we have one-of field in proto file, then at a time only one field can be set
- if we set any other field, the previous field will be unset automatically
