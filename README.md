# student-management-system

osama khalili jag Vi arbetade som en grupp med mohammad naim almani

http://localhost:8080/student-management-system/api/v1/student/{id}	        DELETE student by id 

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/student/{id}	        GET student by id

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/student/{id}	        PATCH ( update email )

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/student	                GET all students

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/student	                POST create student

{
"firstName": " your first name",
"lastName": "your last name",
"email": "you@exemple.com",
"phoneNumber": "your phone number"
}

-----------------------------------------------------------------------------------------------------
 
http://localhost:8080/student-management-system/api/v1/student	                PUT  updat student 

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/student/getbylastname	GET  student by last name 

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/subject                  POST create subject


{
   
    "name" Subject name",

}

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/subject                  Get Subject subject


-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/teacher                  POST create Teacher

{
"firstName": " your first name",
"lastName": "your last name"
}

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/subject/student/{subjectid}/{studentid}      Patch add student to subject

-----------------------------------------------------------------------------------------------------

http://localhost:8080/student-management-system/api/v1/subject/teacher/{subjectid}/{teacherid}      Patch add teacher to subject

