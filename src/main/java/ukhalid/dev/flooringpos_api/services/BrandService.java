package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.entities.Brand;
import ukhalid.dev.flooringpos_api.repositories.BrandRepository;

import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Iterable<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Integer id, Brand brand) {
        Optional<Brand> brandToUpdateOptional = brandRepository.findById(id);
        if (brandToUpdateOptional.isPresent()) {
            Brand brandToUpdate = brandToUpdateOptional.get();
            brandToUpdate.setName(brand.getName());
            return brandRepository.save(brandToUpdate);
        }
        throw new IllegalArgumentException("Product not found");
    }

    public Brand deleteBrand(Integer id) {
        Optional<Brand> brandToDeleteOptiional = brandRepository.findById(id);
        if (brandToDeleteOptiional.isPresent()) {
            Brand brandToDelete = brandToDeleteOptiional.get();
            brandRepository.deleteById(id);
            return brandToDelete;
        }
        throw new IllegalArgumentException("Product not found");
    }
}
