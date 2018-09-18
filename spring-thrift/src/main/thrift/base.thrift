
namespace java com.tian.spring.thrift.api

include "baseVo.thrift"

service BaseService {

   string ping();

   baseVo.PersonVo dealPerson(1:string name,2:string sex,3:i32 age);

}