package com.krushit.common;

public final class Message {
    public static final String INVALID_CONTENT_TYPE = "Invalid Content Type";
    public static final String DRIVER_NOT_EXIST = "Driver Not Exist";
    public static final String GENERIC_ERROR = "Oops something went wong. please try after sometime or contact our support team.";
    public static final String USER_ALREADY_EXIST = "User Already Exist";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String DATABASE_ERROR = "Database Error";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
    public static final String INVALID_ROLE = "Invalid Rode";
    public static final String INVALID_PAYMENT_STATUS = "Invalid Payment Status";
    public static final String INVALID_PAYMENT_MODE = "Invalid Payment Mode";
    public static final String INVALID_FUEL_TYPE = "Invalid Fuel Type";
    public static final String INVALID_DRIVER_VERIFICATION_STATUS = "Invalid Driver Verification Status";

    private Message() {
    }

    public static final class FeedBack {
        public static final String FEEDBACK_SUBMITTED = "Feedback submitted successfully";
        public static final String FEEDBACK_ALREADY_SUBMITTED = "Feedback is already submitted";
        public static final String ERROR_WHILE_ADDING_FEEDBACK = "Error while giving feedback";
        public static final String ERROR_WHILE_FETCHING_TO_FEEDBACK = "Error while fetching to user details";
        public static final String ERROR_WHILE_CHECKING_IS_FEEDBACK_GIVEN = "Error while checking is feedback is given or not";
        public static final String MISSING_RATING_REVIEW_COUNT_PARAMS = "Missing taring & review params";

        private FeedBack() {

        }
    }

    public static final class Ride {
        public static final String LOCATION_IS_REQUIRE = "Please enter source & destination location";
        public static final String RIDE_SERVICES_FETCHED_SUCCESSFULLY = "Available ride services fetched successfully.";
        public static final String PLEASE_ENTER_VALID_LOCATION = "Please Enter Valid Location Id";
        public static final String INVALID_GRAPH_HOPPER_API_RESPONSE = "Graphhopper api response is invalid";
        public static final String COORDINATES_NOT_FOUND_FOR = "Coordinates not found for location: ";
        public static final String RIDE_REQUEST_SUBMITTED_SUCCESSFULLY = "Ride Request Successful";
        public static final String REQUESTED_SERVICE_IS_NOT_AVAILABLE = "Requested vehicle service is not available for this route.";
        public static final String RIDE_REQUEST_NULL = "Ride request cannot be null";
        public static final String INVALID_PICKUP_LOCATION_ID = "Invalid pick-up location";
        public static final String INVALID_DROP_OFF_LOCATION_ID = "Invalid drop-off location";
        public static final String SAME_PICKUP_DROP_OFF = "Pick-up and drop-off locations must be different";
        public static final String INVALID_VEHICLE_SERVICE_ID = "Invalid vehicle service";
        public static final String INVALID_USER_ID = "Invalid user";
        public static final String RIDE_DATE_REQUIRED = "Ride date is required";
        public static final String RIDE_DATE_IN_PAST = "Please enter valid ride date";
        public static final String PICKUP_TIME_REQUIRED = "Pick-up time is required";
        public static final String PICKUP_TIME_IN_PAST = "Please enter valid Pick-up time";
        public static final String PLEASE_ENTER_VALID_RIDE_ID = "Please enter valid ride id";
        public static final String ERROR_WHILE_GETTING_ALL_RIDE_REQUEST_FOR_DRIVER = "Error occur while fetching ride request for driver";
        public static final String ERROR_WHILE_GETTING_RIDE_REQUEST_BY_ID = "Error occur while getting ride request by id";
        public static final String ERROR_WHILE_CREATING_RIDE = "Error occur while creating ride";
        public static final String ERROR_WHILE_RIDE_CANCELLATION = "Error occur while cancelling ride";
        public static final String RIDE_NOT_FOUND_FOR_CANCELLATION = "Ride not found for cancellation";
        public static final String RIDE_CANNOT_BE_CANCELLED = "Sorry this ride is already ";
        public static final String RIDE_CANCELLED = "Ride Successfully Cancelled";
        public static final String RIDE_NOT_BELONG_TO_THIS_CUSTOMER = "Ride id not belong to this customer";
        public static final String RIDE_NOT_BELONG_TO_THIS_DRIVER = "Ride id not belong to this driver";
        public static final String RIDE_ALREADY_STARTED_CANNOT_CANCEL = "Ongoing ride can not be cancelled";
        public static final String RIDES_FETCHED_SUCCESSFULLY = "Ride fetched successfully";
        public static final String ERROR_WHILE_GETTING_ALL_RIDES = "Error occur while getting all rides";
        public static final String ERROR_WHILE_FETCHING_RIDE_STATUS = "Error occur while fetching ride service";
        public static final String PLEASE_ENTER_FEEDBACK_AFTER_RIDE_COMPLETED = "Please submit feedback after ride is completed";
        public static final String PLEASE_ENTER_VALID_RATING = "Please enter valid rating";
        public static final String RIDE_REQUEST_NOT_EXIST = "Sorry, this ride request is not valid";
        public static final String INVALID_DATE_FORMAT = "Invalid date format";
        public static final String DATE_CANNOT_BE_IN_FUTURE = "Please enter valid date";
        public static final String PLEASE_ENTER_VALID_DATE = "Please enter date";
        public static final String ERROR_WHILE_CONVERTING_RIDE_TO_RIDEDTO = "Error occur while converting ride to ridedto";
        public static final String ERROR_WHILE_FETCHING_RIDE_DETAILS_BY_RANGE = "Error occur while getting ride details by range";
        public static final String ERROR_WHILE_FETCHING_TOTAL_RIDES = "Error while fetching total rides";
        public static final String ERROR_WHILE_CALCULATING_DRIVER_TOTAL_EARNING = "error while calculation total driver earning";
        public static final String BOOK_RIDE_IN_ONLY_FIFTEEN_DAYS_IN_ADVANCE = "You can book ride in 15 days is advance";
        public static final String RIDE_ALREADY_SCHEDULED = "You already have a ride scheduled";
        public static final String PLEASE_MANAGE_YOUR_SCHEDULE_ACCORDINGLY = "Please manage your schedule accordingly";
        public static final String DISTANCE_FETCHED_SUCCESSFULLY = "Distance Fetched Successfully";
        public static final String ERROR_WHILE_GETTING_RIDE = "Error while getting ride";
        public static final String YOU_CAN_NOT_GIVE_FEEDBACK_TO_CANCELLED_RIDE = "You can not give feedback to cancelled ride";
        public static final String PLEASE_ENTER_VALID_RIDE_FORMAT = "Invalid ride date format. Expected format: yyyy-MM-dd";
        public static final String SERVICE_NOT_AVAILABLE_FOR_THIS_LOCATION = "Sorry..!! Our service is not available for this location";

        private Ride() {
        }
    }

    public static final class User {
        public static final String INVALID_OPERATION = "Please log in first";
        public static final String USER_LOGOUT_SUCCESSFULLY = "Logout successfully";
        public static final String ERROR_WHILE_REGISTERING_USER = "Error occur while registering user";
        public static final String PLEASE_ENTER_VALID_EMAIL_OR_PASS = "Please enter valid email & password";
        public static final String ERROR_WHILE_USER_LOGIN = "Error while user login";
        public static final String ERROR_WHILE_VALIDATING_USER = "Error while validating user";
        public static final String ERROR_WHILE_CHECKING_USER_EXISTENCE = "Error while checking user existence";
        public static final String ERROR_WHILE_GET_USER_DETAILS = "Error while getting user details";
        public static final String ERROR_WHILE_GETTING_ALL_CUSTOMERS = "Error while getting all customers";
        public static final String ERROR_WHILE_GETTING_DISPLAY_ID = "Error occur while getting user display id";
        public static final String ERROR_WHILE_GETTING_USER_FULL_NAME = "Error while getting user full name";
        public static final String PLEASE_ENTER_PASSWORD = "Please Enter Password";
        public static final String PLEASE_ENTER_PHONE_NO = "Please Enter Phone no";
        public static final String PLEASE_ENTER_EMAIL = "Please Enter Email";
        public static final String PLEASE_ENTER_FIRST_NAME = "Please Enter First Name";
        public static final String PLEASE_ENTER_LASE_NAME = "Please Enter Last Name";
        public static final String USER_NOT_FOUND = "User not exist";
        public static final String ENTER_VALID_USER_DATA = "Please Enter Valid User Data";
        public static final String PLEASE_ENTER_VALID_PASSWORD = "Password must contain at least one uppercase letter, one special character, and one digit.";
        public static final String PLEASE_ENTER_VALID_PHONE_NO = "Please enter a valid phone number.";
        public static final String PLEASE_ENTER_VALID_EMAIL = "Please enter a valid email.";
        public static final String USER_REGISTERED_SUCCESSFULLY = "User Registration Successful";
        public static final String EMAIL_AND_PASS_REQUIRED = "Email & Password are Required";
        public static final String LOGIN_SUCCESSFUL = "Login Successful";
        public static final String PASSWORD_MISMATCHED = "Incorrect old password";
        public static final String PASSWORD_CHANGED_SUCCESSFULLY = "Password changed successfully";
        public static final String INVALID_USER_ID = "Invalid user id";
        public static final String ERROR_WHILE_BLOCKING_USER = "Error occur while blocking user";
        public static final String ERROR_WHILE_CHECK_IS_USER_BLOCKED = "Error occur while checking user is blocked or not";
        public static final String YOUR_ACCOUNT_IS_SUSPENDED_PLEASE_CONTACT_SUPPORT = "Your account is suspended please contact support";
        public static final String ERROR_WHILE_UPDATING_USER_DETAILS = "Error while updating user details";
        public static final String ERROR_WHILE_UPDATING_PASSWORD = "Error while updating password";
        public static final String ERROR_WHILE_UPDATE_DRIVER_RATING = "Error while updating driver rating";
        public static final String DETAILS_UPDATED_SUCCESSFULLY = "User Details Updated Successfully";
        public static final String ERROR_WHILE_GETTING_CUSTOMERS_BY_RATING = "Error while getting user ratings";
        public static final String DRIVER_ADDED_TO_FAVOURITE_LIST = "Driver added to favourites";
        public static final String DRIVER_NOT_FOUND = "Driver not found";
        public static final String ERROR_WHILE_ADDING_FAVOURITE_DRIVER = "Error while adding favourite driver";
        public static final String DRIVER_ALREADY_FAVOURITE = "Driver is already in favourite list";
        public static final String ERROR_WHILE_VALIDATING_FAVOURITE_DRIVER = "Error while validating driver";
        public static final String ERROR_WHILE_DELETING_FAVOURITE_DRIVER = "Error while deleting favourite driver";
        public static final String NO_USERS_FOUND = "No Users found";
        public static final String SUCCESSFULLY_RETRIEVED_USERS = "Successfully retrieved users";
        public static final String PLEASE_ENTER_VALID_PASSWORD_LENGTH = "Password length between 8 and 20";

        private User() {
        }
    }

    public static final class Auth {
        public static final String SESSION_EXPIRED = "Session Expired";
        public static final String PLEASE_LOGIN = "Please login";
        public static final String UNAUTHORIZED = "You don't have valid privilege to perform this operation";

        private Auth() {
        }
    }

    public static final class Driver {
        public static final String ERROR_WHILE_UPDATING_DRIVER_AVAILABILITY = "Error occur while updating driver availability";
        public static final String ERROR_WHILE_CHECKING_LICENCE_NUMBER = "Error while checking licence number";
        public static final String ERROR_FOR_CHECKING_DRIVER_DOCUMENT_UPLOADED = "Error occur while checking driver document";
        public static final String ERROR_FOR_CHECKING_DRIVER_DOCUMENT_VERIFIED = "Error occur while checking driver document verification";
        public static final String ERROR_FOR_GETTING_DRIVER_ID_FROM_USER_ID = "Error while getting driver id from user id";
        public static final String ERROR_WHILE_FETCHING_ALL_DRIVERS = "Error while fetching all drivers";
        public static final String PLEASE_PERFORM_VALID_VERIFICATION_OPERATION = "Please enter valid approval request : only [ACCEPT : REJECT] accepted";
        public static final String ERROR_WHILE_VERIFYING_DRIVER = "Error occur while verification of driver";
        public static final String ERROR_WHILE_CHECKING_DRIVER_EXISTENCE = "Error occur while checking driver existence";
        public static final String ERROR_WHILE_GETTING_PENDING_VERIFICATION_DRIVER = "Error while getting pending driver verification";
        public static final String ERROR_WHILE_INSERT_DRIVER_DETAILS = "Error while inserting driver details";
        public static final String LICENCE_NUMBER_IS_REQUIRED = "Please enter licence number";
        public static final String ENTER_VALID_LICENCE_NUMBER = "Please enter valid licence number";
        public static final String UPLOAD_VALID_LICENCE_PHOTO = "Licence photo file does not exist";
        public static final String LICENCE_NUMBER_IS_ALREADY_EXIST = "Driver already registered with this licence number";
        public static final String LICENCE_PHOTO_PATH_IS_REQUIRD = "Licence photo not exist at : ";
        public static final String FAILED_TO_STORE_DOCUMENT = "Failed to store licence photo: ";
        public static final String DOCUMENT_NOT_VERIFIED = "Driver document not verified";
        public static final String INVALID_DRIVER_ID = "Please enter valid driver id";
        public static final String NO_DRIVERS_FOUND = "No drivers found in system";
        public static final String SUCCESSFULLY_RETRIEVED_DRIVERS = "Successfully Retrieved Driver";
        public static final String VERIFICATION_DONE_SUCCESSFUL = "Driver verification updated successfully!";
        public static final String DOCUMENT_STORED_SUCCESSFULLY = "Document Stored Successfully and Under Review, it may take 24-48 Hours to verify";
        public static final String DOCUMENT_NOT_UPLOADED = "Driver document not uploaded";
        public static final String NO_PENDING_VERIFICATION = "No pending driver verifications.";
        public static final String SUCCESSFULLY_RETRIEVE_DRIVERS = "Pending driver verifications retrieved successfully.";
        public static final String INVALID_FILE_TYPE = "Invalid content type JPG, JPEG, PNG are allowed";
        public static final String DOCUMENT_IS_UNDER_REVIEW = "Your document is under review please wait for approval.";
        public static final String ERROR_WHILE_CHECKING_IS_DOCUMENT_UNDER_REVIEW = "Error occur while checking is driver document under review";
        public static final String PLEASE_DOCUMENT_NOT_UPLOADED = "Your document not uploaded, please upload it first..!!";

        private Driver() {
        }
    }

    public static final class Customer {
        public static final String CUSTOMER_PATH = "/userSignUp";
        public static final String NO_CUSTOMER_FOUND = "No users found";
        public static final String SUCCESSFULLY_RETRIEVED_CUSTOMER = "Successfully Retrieved Customers";

        private Customer() {
        }
    }

    public static final class Vehicle {
        public static final String BRAND_MODEL_ALREADY_EXISTS = "This model of this brand already exist";
        public static final String SUCCESSFULLY_RETRIEVED_ALL_BRAND_MODELS = "Successfully retrieve all brand models";
        public static final String ERROR_OCCUR_WHILE_CHECKING_BRAND_MODEL = "Error occur while checking brand model";
        public static final String ERROR_OCCUR_WHILE_CHECKING_MIN_YEAR = "Error occur while checking min year for brand model";
        public static final String BRAND_MODEL_NOT_SUPPORTED = "Sorry, Your vehicle brand model not supported by system";
        public static final String PENDING = "Pending";
        public static final String ERROR_OCCUR_WHILE_CHECKING_SERVICE = "Error occur while checking service existence";
        public static final String ERROR_OCCUR_WHILE_CHECKING_MODEL = "Error occur while checking brand model existence";
        public static final String ERROR_OCCUR_WHILE_GETTING_ALL_BRAND_MODELS = "Error occur while getting all brand models";
        public static final String BRAND_MODEL_YEAR_NOT_SUPPORTED = "Sorry, this brand model registration year is not supported";
        public static final String NO_REQUEST_FOUND = "No request found";
        public static final String FETCHING_ALL_REQUEST_SUCCESSFULLY = "Successfully fetched all requests";
        public static final String ERROR_OCCUR_WHILE_ADDING_VEHICLE = "Error Occur While Adding Vehicle";
        public static final String PLEASE_ENTER_VALID_BASE_FARE = "Please Enter Valid Base Fare";
        public static final String PLEASE_ENTER_VALID_PER_KM_RATE = "Please enter valid per km rate";
        public static final String VEHICLE_SERVICE_IS_REQUIRED = "Please Enter Vehicle Service";
        public static final String INVALID_PASSENGER_CAPACITY = "Passenger capacity with 1 to 8";
        public static final String PLEASE_ENTER_VALID_COMMISSION_PERCENTAGE = "Please Enter Valid Commission percentage";
        public static final String VEHICLE_SERVICE_ADDED_SUCCESSFULLY = "Service added successfully";
        public static final String VEHICLE_SERVICE_ALREADY_EXISTS = "Vehicle Service is already exist";
        public static final String BRAND_MODEL_ADDED_SUCCESSFULLY = "Brand Model Added Successfully";
        public static final String VEHICLE_REGISTERED_SUCCESSFULLY = "Vehicle Registered Successfully";
        public static final String PLEASE_ENTER_VALID_VERIFICATION_REQUEST = "Please enter valid verification request";
        public static final String PLEASE_ENTER_VALID_VERIFICATION_STATUS = "Please enter valid verification status, Allowed : Verified, Reject";
        public static final String PLEASE_ENTER_REJECTION_MESSAGE = "Please rejection message";
        public static final String VEHICLE_DATA_MISSING = "Invalid request! Vehicle data is missing.";
        public static final String BRAND_MODEL_ID_INVALID = "Please enter valid brand model id";
        public static final String REGISTRATION_NUMBER_REQUIRED = "Registration number is required.";
        public static final String REGISTRATION_NUMBER_INVALID = "Please enter valid registration number, example: GJ12AB1234.";
        public static final String YEAR_INVALID = "Invalid manufacturing year, between 1990 and current year.";
        public static final String FUEL_TYPE_INVALID = "Please enter valid fuel type. Allowed : Petrol, Diesel, CNG, Electric, Hybrid.";
        public static final String TRANSMISSION_TYPE_INVALID = "Please enter valid transmission type. Allowed : Manual, Automatic.";
        public static final String GROUND_CLEARANCE_INVALID = "Please enter valid ground clearance";
        public static final String WHEEL_BASE_INVALID = "Please enter valid wheel base";
        public static final String PLEASE_ENTER_VALID_BRAND_MODEL = "Please enter a valid brand model.";
        public static final String PLEASE_ENTER_VALID_SERVICE_ID = "Please enter a valid service ID.";
        public static final String BRAND_NAME_IS_REQUIRED = "Brand name is required.";
        public static final String MODEL_NAME_IS_REQUIRED = "Model name is required.";
        public static final String PLEASE_ENTER_VALID_MIN_YEAR = "Please enter a valid minimum year.";
        public static final String INVALID_VEHICLE_TYPE = "Please Enter Valid Vehicle Type : Only 2W, 3W, 4W are allowed";
        public static final String ERROR_OCCUR_WHILE_ADDING_SERVICE = "Error Occur While Adding Vehicle Service";
        public static final String ERROR_OCCUR_WHILE_ADDING_MODEL = "Error Occur While Adding Vehicle Model";
        public static final String ERROR_OCCUR_WHILE_CHECK_VEHICLE_EXISTENCE = "Error checking driver vehicle existence.";
        public static final String DRIVER_VEHICLE_ALREADY_EXIST = "Driver vehicle already exist";
        public static final String PLEASE_ENTER_VALID_VEHICLE_SERVICE = "Please enter valid vehicle service";
        public static final String ERROR_OCCUR_WHILE_DELETING_VEHICLE = "Error occur while deleting vehicle";
        public static final String VEHICLE_NOT_EXIST = "Driver vehicle not exist";
        public static final String VEHICLE_SERVICE_NOT_EXIST = "Vehicle service not exist";
        public static final String RIDE_ACCEPTED = "Ride Accepted..!!";
        public static final String ERROR_OCCUR_WHILE_UPDATING_RIDE_REQUEST_STATUS = "Update ride request status";
        public static final String ERROR_OCCUR_WHILE_BOOKING_RIDE = "Error while booking ride";
        public static final String ERROR_OCCUR_WHILE_GETTING_ALL_BRANDS = "Error while getting all brands";
        public static final String ERROR_OCCUR_WHILE_GETTING_BRAND_MODEL_FROM_BRAND = "Error while getting brand model from brand";
        public static final String ERROR_OCCUR_WHILE_FETCHING_VEHICLE_SERVICES = "Error while fetching vehicle services";
        public static final String SUCCESSFULLY_RETRIEVED_BRANDS = "Successfully retrieved all brands";
        public static final String NO_BRANDS_FOUND = "No Brands found";

        private Vehicle() {
        }
    }

    public static final class Database {
        public static final String FLYWAY_SUCCESSFUL = "Flyway set up successful";
        public static final String FLYWAY_FAILED = "Failed to set up flyway";
        public static final String DRIVER_NOT_FOUND = "Driver not found";
        public static final String CONNECTION_SUCCESSFUL = "Database connection successful";

        private Database() {
        }
    }

    public static final class Location {
        public static final String LOCATION_ADDED_SUCCESSFULLY = "Location Added Successfully";
        public static final String LOCATIONS_NOT_FOUND = "Locations not found";
        public static final String SUCCESSFULLY_RETRIEVED_ALL_LOCATIONS = "Successfully retrieved locations";
        public static final String LOCATION_NOT_FOUND = "Location not found for deletion";
        public static final String LOCATION_DELETED_SUCCESSFULLY = "Location deleted successfully";
        public static final String USER_BLOCKED_SUCCESSFULLY = "User blocked successfully";
        public static final String ERROR_WHILE_ADDING_LOCATION = "Error while adding location";
        public static final String ERROR_WHILE_GETTING_LOCATION_BY_NAME = "Error while getting location by name";
        public static final String ERROR_WHILE_GETTING_ALL_LOCATION = "Error while getting all location";
        public static final String ERROR_WHILE_DELETING_LOCATION = "Error while deleting location";
        public static final String ERROR_WHILE_GET_COMMISSION_BY_DISTANCE = "Error while get commission by distance";
        public static final String ERROR_WHILE_CHECKING_LOCATION_IS_ACTIVE_OR_NOT = "Error while checking location is active or not";
        public static final String LOCATION_ACTIVATED_SUCCESSFULLY = "Location activated successfully";
        public static final String LOCATION_DEACTIVATED_SUCCESSFULLY = "Location deactivated successfully";

        private Location() {
        }
    }

    public static final class CORSConstants {
        public static final String HEADER_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
        public static final String HEADER_ALLOW_METHODS = "Access-Control-Allow-Methods";
        public static final String HEADER_ALLOW_HEADERS = "Access-Control-Allow-Headers";
        public static final String HEADER_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";

        public static final String VALUE_ALLOW_ALL = "*";
        public static final String VALUE_ALLOW_METHODS = "GET, POST, PUT, DELETE, OPTIONS, PATCH";
        public static final String VALUE_ALLOW_HEADERS = "Content-Type, Authorization";
        public static final String VALUE_ALLOW_CREDENTIALS = "true";

        private CORSConstants() {

        }
    }
}
