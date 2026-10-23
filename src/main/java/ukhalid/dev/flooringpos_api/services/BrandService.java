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

    public void deleteBrand(Integer id) {
        Optional<Brand> brandToDeleteOptional = brandRepository.findById(id);
        if (brandToDeleteOptional.isPresent()) {
            Brand brandToDelete = brandToDeleteOptional.get();
            brandRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Brand not found");
    }
}
