# Rent-a-Car-Agent

## Services


### Authentication service

  - User
  - Authority
  - Company
  
    #### Endpoints:
    
      - /auth/user/login  &nbsp; POST: Login
      - /auth/user    POST: Registration
      - /permission  POST: Add permission
      - /permission        PUT: Update permissio
      - /user              GET: Get all users
      - /user/{id}            GET: Get specific user
      - /user                 PUT: Update user
      - /user/changePassword/{id}  PUT: Update password
      - /user/changeStatus/{id}    PUT: Update user status (activated, blocked, deleted) 
      - /company/{id}  PUT: Update company
      - /company  POST: Add company
      - /company  GET: Get company
      

### Advertisement service
    
  - Advertisement
  - CarBrand
  - CarClass
  - CarModel
  - Transmission
  - FuelType
  - Car
  - Term
  - PriceList
  
     #### Endpoints:
     
      - /advertisement GET: Get all active advertisements
      - /advertisement POST: Add advertisement
      - /advertisement/{id} PUT: Update advertisement
      - /advertisement/owner/{id} GET: Get advertisements of owner 
      - /carBrand POST: Add car brand
      - /carBrand/{id} DELETE: Delete car brand
      - /carBrand/{id} GET: Get car brand
      - /carBrand/{id}/carModel GET: Get car model
      - /carClass POST: Add car class
      - /carClass/{id} DELETE: Delete car class
      - /carModel POST: Add car model
      - /carModel/{id} DELETE: Delete car model
      - /transmission POST: Add transmission
      - /transmission/{id}  DELETE: Delete transmission
      - /fuelType POST: Add fuel type
      - /fuelType  DELETE: Delete fuel type
      - /priceList GET: Get all price lists
      - /priceList POST: Add price list
      - /priceList/{id} PUT: Update price list
      - /priceList/{id} GET: Get price list


### Rent service

  - RentRequest
  - RequestHolder
  
    #### Endpoints:
    
      - /rentRequest POST: Add rent request
      - /rentRequest/history/{id} GET: Get history rents
      - /rentRequest/cancelable/{id} GET: Get cancelable rents
      - /rentRequest/request/{confirm} POST: Confirm or cancel request
      - /rentRequest/bundle/{confirm} POST: Confirm or cancel bundle
      - /requestHolder/{id} GET: Get request holder
      
  
### Search advertisement service
  
   #### Endpoints:
   - /advertisement/search POST: Advertisement search

### Statistics service

  - Comment
  - Rate
  - RentStatistic
  
     #### Endpoints:
     - /comment POST: Add comment
     - /comment GET: Get unprocessed comments
     - /comment/{id} PUT: Update status
     - /rate POST: Add rate
     - /statistic/{id} GET: Get statistic


### Message service

  - Messages
  
     #### Endpoints:
      - /message/owner/{id} GET: Get owner messages
      - /message POST: Send message
 
 
### Car location Service

   #### Endpoints:
   - /carLocation GET: Get car location
   - /carLocation POST: Add car location
