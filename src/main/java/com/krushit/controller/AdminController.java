package com.krushit.controller;

import com.krushit.common.CurrentUserContext;
import com.krushit.common.Message;
import com.krushit.common.annotations.AdminOnly;
import com.krushit.common.exception.ApplicationException;
import com.krushit.common.exception.ValidationException;
import com.krushit.common.mapper.Mapper;
import com.krushit.common.utils.AuthUtils;
import com.krushit.controller.validator.*;
import com.krushit.dto.*;
import com.krushit.entity.Location;
import com.krushit.entity.User;
import com.krushit.entity.VehicleService;
import com.krushit.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AdminOnly
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationService2 locationService2;
    @Autowired
    private VehicleRideService vehicleRideService;
    @Autowired
    private UserService userService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private Mapper mapper;
    @Autowired
    private BrandModelValidator brandModelValidator;
    @Autowired
    private VehicleServiceValidator vehicleServiceValidator;
    @Autowired
    private DateValidator dateValidator;
    @Autowired
    private DriverVerificationRequestValidator driverVerificationRequestValidator;
    @Autowired
    private DistanceCalculatorValidator distanceCalculatorValidator;

    @PostMapping("/brand-model")
    public ResponseEntity<?> addBrandModel(@RequestBody BrandModelRequestDTO brandModel) throws ApplicationException {
        User user = CurrentUserContext.getCurrentUser();
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
        brandModelValidator.validate(brandModel, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        vehicleRideService.addBrandModel(brandModel);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Vehicle.BRAND_MODEL_ADDED_SUCCESSFULLY));
    }

    @PostMapping("/location")
    public ResponseEntity<?> addLocation(@RequestBody Location location) throws ApplicationException {
        locationService2.addLocation(location.getName());
        return ResponseEntity.ok(new ApiResponseDTO(Message.Location.LOCATION_ADDED_SUCCESSFULLY));
    }

    @PostMapping("/vehicle-service")
    public ResponseEntity<?> addVehicleService(@RequestBody VehicleServiceDTO vehicleServiceDTO) throws ApplicationException {
        User user = CurrentUserContext.getCurrentUser();
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
        vehicleServiceValidator.validate(vehicleServiceDTO, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        VehicleService vehicleService = mapper.convertToEntity(vehicleServiceDTO);
        vehicleRideService.addVehicleService(vehicleService);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Vehicle.VEHICLE_SERVICE_ADDED_SUCCESSFULLY));
    }

    @PatchMapping("/location/activate")
    public ResponseEntity<?> activateLocation(@RequestParam(name = "locationId") int locationId) throws ApplicationException {
        locationService2.activateLocation(locationId);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Location.LOCATION_ACTIVATED_SUCCESSFULLY));
    }

    @PatchMapping("/location/deactivate")
    public ResponseEntity<?> deactivateLocation(@RequestParam int locationId) throws ApplicationException {
        locationService.inactivateLocation(locationId);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Location.LOCATION_DEACTIVATED_SUCCESSFULLY));
    }

    @PatchMapping("/user/block")
    public ResponseEntity<?> blockUser(@RequestParam int userId) throws ApplicationException {
        userService.blockUser(userId);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Location.USER_BLOCKED_SUCCESSFULLY));
    }

    @PostMapping("/distance")
    public ResponseEntity<?> calculateDistance(@RequestBody DistanceCalculatorDTO dto) throws Exception {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, "distanceCalculatorDTO");
        distanceCalculatorValidator.validate(dto, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        double distance = locationService.calculateDistance(dto.getFrom(), dto.getTo());
        return ResponseEntity.ok(new ApiResponseDTO(Message.Ride.DISTANCE_FETCHED_SUCCESSFULLY, distance));
    }

    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrands() throws ApplicationException {
        List<String> brands = vehicleRideService.getAllBrands();
        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseDTO(Message.Vehicle.NO_BRANDS_FOUND));
        }
        return ResponseEntity.ok(new ApiResponseDTO(Message.Vehicle.SUCCESSFULLY_RETRIEVED_BRANDS, brands));
    }

    @GetMapping("/customers-by-rating")
    public ResponseEntity<?> getAllCustomersByRating(@RequestParam String rating,
                                                     @RequestParam(name = "reviews") String totalRating) throws ApplicationException {
        if (rating.isEmpty() || totalRating.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO(Message.FeedBack.MISSING_RATING_REVIEW_COUNT_PARAMS));
        }
        int ratingThreshold = Integer.parseInt(rating);
        int reviewThreshold = Integer.parseInt(totalRating);
        List<UserDTO> users = userService.getUsersWithLessRatingAndReviews(ratingThreshold, reviewThreshold);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseDTO(Message.User.NO_USERS_FOUND));
        }
        return ResponseEntity.ok(new ApiResponseDTO(Message.User.SUCCESSFULLY_RETRIEVED_USERS, users));
    }

    @GetMapping("/drivers")
    public ResponseEntity<?> getAllDrivers() throws ApplicationException {
        List<DriverDTO> drivers = driverService.getAllDrivers();
        if (drivers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseDTO(Message.Driver.NO_DRIVERS_FOUND));
        }
        return ResponseEntity.ok(new ApiResponseDTO(Message.Driver.SUCCESSFULLY_RETRIEVED_DRIVERS, drivers));
    }

    @GetMapping("/locations")
    public ResponseEntity<?> getAllLocations() throws ApplicationException {
        List<Location> locations = locationService2.getAllLocations();
        return ResponseEntity.ok(new ApiResponseDTO(Message.Location.SUCCESSFULLY_RETRIEVED_ALL_LOCATIONS, locations));
    }

    @GetMapping("/locationsByPage")
    public ResponseEntity<?> getAllLocationsByPagination(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws ApplicationException {
        List<Location> locations = locationService2.getAllLocationsByPagenation(pageNumber, pageSize);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Location.SUCCESSFULLY_RETRIEVED_ALL_LOCATIONS, locations));
    }

    @GetMapping("/brand-models-driver")
    public ResponseEntity<?> getAllModel() throws ApplicationException {
        List<BrandModelResponseDTO> brandModels = vehicleRideService.getAllBrandModel();
        return ResponseEntity.ok(new ApiResponseDTO(Message.Vehicle.SUCCESSFULLY_RETRIEVED_ALL_BRAND_MODELS, brandModels));
    }

    @GetMapping("/brand-models")
    public ResponseEntity<?> getAllModels() throws ApplicationException {
        List<BrandModelsResponseDTO> brandModels = vehicleRideService.getAllBrandModels();
        return ResponseEntity.ok(new ApiResponseDTO(Message.Vehicle.SUCCESSFULLY_RETRIEVED_ALL_BRAND_MODELS, brandModels));
    }

    @GetMapping("/pending-verification")
    public ResponseEntity<?> getAllPendingVerification() throws ApplicationException {
        List<PendingDriverDTO> pendingDrivers = driverService.getPendingVerificationDrivers();
        if (pendingDrivers.isEmpty()) {
            return ResponseEntity.ok(new ApiResponseDTO(Message.Driver.NO_PENDING_VERIFICATION));
        }
        return ResponseEntity.ok(new ApiResponseDTO(Message.Driver.SUCCESSFULLY_RETRIEVE_DRIVERS, pendingDrivers));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() throws ApplicationException {
        List<UserDTO> users = userService.getAllCustomers();
        if (users.isEmpty()) {
            return ResponseEntity.ok(new ApiResponseDTO(Message.Customer.NO_CUSTOMER_FOUND, null));
        }
        return ResponseEntity.ok(new ApiResponseDTO(Message.Customer.SUCCESSFULLY_RETRIEVED_CUSTOMER, users));
    }

    @GetMapping("/vehicles/services")
    public ResponseEntity<?> getAllVehicleServices() throws ApplicationException {
        List<VehicleService> services = vehicleRideService.getAllVehicleServices();
        return ResponseEntity.ok(new ApiResponseDTO(Message.Location.SUCCESSFULLY_RETRIEVED_ALL_LOCATIONS, services));
    }

    @GetMapping("/vehicles/models")
    public ResponseEntity<?> getBrandModelByBrand(
            @RequestParam("brand") String brand) throws ApplicationException {
        List<String> models = vehicleRideService.getModelsByBrand(brand);
        if (models.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseDTO(Message.Vehicle.NO_BRANDS_FOUND, null));
        }
        return ResponseEntity.ok(new ApiResponseDTO(Message.Vehicle.SUCCESSFULLY_RETRIEVED_BRANDS, models));
    }

    @GetMapping("/rides/income")
    public ResponseEntity<?> getRideDetailsByRange(@RequestParam("startDate") String startDateStr,
                                                   @RequestParam("endDate") String endDateStr) throws ApplicationException {
        User user = CurrentUserContext.getCurrentUser();
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
        dateValidator.validate(startDateStr, errors);
        dateValidator.validate(endDateStr, errors);
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        DateRangeIncomeResponseDTO dto = vehicleRideService.getIncomeByDateRange(0, startDate, endDate);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Vehicle.VEHICLE_REGISTERED_SUCCESSFULLY, dto));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) throws ApplicationException {
        Optional<User> userOpt = userService.getUserDetails(userId);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDTO(Message.Customer.SUCCESSFULLY_RETRIEVED_CUSTOMER, userOpt.get()));
        } else {
            return ResponseEntity.ok(new ApiResponseDTO(Message.Customer.NO_CUSTOMER_FOUND, null));
        }
    }

    @GetMapping("/users/by-page")
    public ResponseEntity<?> getUsersByPageAndData(@RequestParam("page") int page,
                                                   @RequestParam("data") int dataPerPage) throws ApplicationException {
        List<User> users = userService.getCustomersByPage(page, dataPerPage);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseDTO(Message.Customer.NO_CUSTOMER_FOUND, null));
        }
        return ResponseEntity.ok(new ApiResponseDTO(Message.Customer.SUCCESSFULLY_RETRIEVED_CUSTOMER, users));
    }

    @GetMapping("/users/by-offset")
    public ResponseEntity<?> getUsersByOffsetAndLimit(@RequestParam("offset") int offset,
                                                      @RequestParam("limit") int limit) throws ApplicationException {
        List<User> users = userService.getCustomersByOffsetAndLimit(offset, limit);
        return ResponseEntity.ok(new ApiResponseDTO(
                users.isEmpty() ? Message.Customer.NO_CUSTOMER_FOUND : Message.Customer.SUCCESSFULLY_RETRIEVED_CUSTOMER,
                users.isEmpty() ? null : users
        ));
    }

    @PutMapping("/driver/verify")
    public ResponseEntity<?> verifyDriverDocument(@RequestParam("driverId") int driverId,
                                                  @RequestBody DriverVerificationRequestDTO verificationRequest) throws ApplicationException {
        User user = CurrentUserContext.getCurrentUser();
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
        driverVerificationRequestValidator.validate(verificationRequest, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        driverService.verifyDriver(verificationRequest, driverId);
        return ResponseEntity.ok(new ApiResponseDTO(Message.Driver.VERIFICATION_DONE_SUCCESSFUL, null));
    }
}
