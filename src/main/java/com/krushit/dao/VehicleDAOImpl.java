package com.krushit.dao;

import com.krushit.common.Message;
import com.krushit.common.config.JPAConfig;
import com.krushit.common.exception.DBException;
import com.krushit.dto.BrandModelResponseDTO;
import com.krushit.dto.BrandModelsResponseDTO;
import com.krushit.entity.BrandModel;
import com.krushit.entity.RideRequest;
import com.krushit.entity.Vehicle;
import com.krushit.entity.VehicleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class VehicleDAOImpl implements IVehicleDAO {
    private static final String COUNT_VEHICLE_SERVICE_BY_NAME =
            "SELECT COUNT(vs) FROM VehicleService vs WHERE LOWER(vs.serviceName) = :name";
    private static final String COUNT_BRAND_MODEL_BY_NAME_AND_MODEL =
            "SELECT COUNT(bm) FROM BrandModel bm WHERE bm.brandName = :brandName AND bm.model = :model";
    private static final String COUNT_VEHICLE_BY_DRIVER_ID =
            "SELECT COUNT(v) FROM Vehicle v WHERE v.driver.driverId = :driverId";
    private static final String SELECT_ALL_BRAND_MODELS =
            "SELECT bm FROM BrandModel bm";
    private static final String GET_ALL_BRANDS =
            "SELECT DISTINCT b.brandName FROM BrandModel b";
    private static final String SELECT_AVAILABLE_VEHICLE_SERVICES =
            "SELECT DISTINCT bm.vehicleService FROM BrandModel bm " +
                    "JOIN bm.vehicles v " +
                    "JOIN v.driver d " +
                    "WHERE d.isAvailable = true";
    private static final String DELETE_VEHICLE_BY_USER_ID =
            "DELETE FROM Vehicle v WHERE v.driver.user.userId = :userId";
    private static final String GET_BRAND_MODELS_BY_BRAND =
            "SELECT DISTINCT b.model FROM BrandModel b WHERE b.brandName = :brandName";
    private static final String GET_ALL_VEHICLE_SERVICES = "SELECT v FROM VehicleService v";

    @Override
    public boolean isVehicleServiceExists(String serviceName) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            Long count = em.createQuery(COUNT_VEHICLE_SERVICE_BY_NAME, Long.class)
                    .setParameter("name", serviceName.toLowerCase())
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_CHECKING_SERVICE, e);
        }
    }

    @Override
    public void addVehicleService(VehicleService vehicleService) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(vehicleService);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_ADDING_SERVICE, e);
            }
        }
    }

    @Override
    public boolean isBrandModelExists(String brandName, String model) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            Long count = em.createQuery(COUNT_BRAND_MODEL_BY_NAME_AND_MODEL, Long.class)
                    .setParameter("brandName", brandName)
                    .setParameter("model", model)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_CHECKING_MODEL, e);
        }
    }

    @Override
    public boolean isBrandModelExistsByID(int brandModelId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.find(BrandModel.class, brandModelId) != null;
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_CHECKING_BRAND_MODEL, e);
        }
    }

    @Override
    public void addBrandModel(BrandModel brandModel) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(brandModel);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_ADDING_MODEL, e);
            }
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(vehicle);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_ADDING_VEHICLE, e);
            }
        }
    }

    @Override
    public boolean isDriverVehicleExist(int driverID) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            int count = em.createQuery(COUNT_VEHICLE_BY_DRIVER_ID, int.class)
                    .setParameter("driverId", driverID)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_CHECK_VEHICLE_EXISTENCE, e);
        }
    }

    @Override
    public List<BrandModelsResponseDTO> getAllBrandModels() throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            List<BrandModel> brandModels = em.createQuery(SELECT_ALL_BRAND_MODELS, BrandModel.class).getResultList();
            Map<String, List<String>> brandToModels = new HashMap<>();
            Map<String, List<String>> brandToServices = new HashMap<>();
            for (BrandModel bm : brandModels) {
                String brand = bm.getBrandName();
                String model = bm.getModel();
                String serviceName = bm.getVehicleService().getServiceName();
                brandToModels.computeIfAbsent(brand, k -> new ArrayList<>()).add(model);
                brandToServices.computeIfAbsent(brand, k -> new ArrayList<>()).add(serviceName);
            }
            List<BrandModelsResponseDTO> response = new ArrayList<>();
            for (String brand : brandToModels.keySet()) {
                List<String> models = brandToModels.get(brand);
                List<String> services = new ArrayList<>(brandToServices.getOrDefault(brand, new ArrayList<>()));
                response.add(new BrandModelsResponseDTO(brand, models, services));
            }
            return response;
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_GETTING_ALL_BRAND_MODELS, e);
        }
    }

    @Override
    public List<BrandModelResponseDTO> getAllBrandModel() throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            List<BrandModel> brandModels = em.createQuery(SELECT_ALL_BRAND_MODELS, BrandModel.class).getResultList();
            Set<String> uniqueBrandModelKeys = new HashSet<>();
            List<BrandModelResponseDTO> response = new ArrayList<>();
            for (BrandModel bm : brandModels) {
                String brand = bm.getBrandName();
                String model = bm.getModel();
                String serviceName = bm.getVehicleService().getServiceName();
                int brandModelId = bm.getBrandModelId();
                String key = brand + "|" + model;
                if (!uniqueBrandModelKeys.contains(key)) {
                    uniqueBrandModelKeys.add(key);
                    response.add(new BrandModelResponseDTO(brandModelId, brand, model, serviceName));
                }
            }
            return response;
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_GETTING_ALL_BRAND_MODELS, e);
        }
    }


    @Override
    public List<String> getAllBrands() throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(GET_ALL_BRANDS, String.class).getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_GETTING_ALL_BRANDS, e);
        }
    }

    @Override
    public List<String> getModelsByBrand(String brandName) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(
                            GET_BRAND_MODELS_BY_BRAND, String.class)
                    .setParameter("brandName", brandName)
                    .getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_GETTING_BRAND_MODEL_FROM_BRAND + brandName, e);
        }
    }

    @Override
    public int getMinYearForBrandModel(int brandModelId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            BrandModel bm = em.find(BrandModel.class, brandModelId);
            return bm != null ? bm.getMinYear() : 0;
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_CHECKING_MIN_YEAR, e);
        }
    }

    @Override
    public List<VehicleService> getAllAvailableVehicleServices() throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(SELECT_AVAILABLE_VEHICLE_SERVICES, VehicleService.class).getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_CHECKING_BRAND_MODEL, e);
        }
    }

    @Override
    public void requestForRide(RideRequest rideRequest) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(rideRequest);
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_BOOKING_RIDE, e);
            }
        }
    }

    @Override
    public Optional<VehicleService> getVehicleService(int serviceId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            VehicleService service = em.find(VehicleService.class, serviceId);
            return Optional.ofNullable(service);
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_CHECKING_BRAND_MODEL, e);
        }
    }

    @Override
    public void deleteVehicleByUserId(int userId) throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.createQuery(DELETE_VEHICLE_BY_USER_ID)
                        .setParameter("userId", userId)
                        .executeUpdate();
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_DELETING_VEHICLE, e);
            }
        }
    }

    @Override
    public List<VehicleService> getAllVehicleServices() throws DBException {
        try (EntityManager em = JPAConfig.getEntityManagerFactory().createEntityManager()) {
            TypedQuery<VehicleService> query = em.createQuery(GET_ALL_VEHICLE_SERVICES, VehicleService.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DBException(Message.Vehicle.ERROR_OCCUR_WHILE_FETCHING_VEHICLE_SERVICES, e);
        }
    }
}