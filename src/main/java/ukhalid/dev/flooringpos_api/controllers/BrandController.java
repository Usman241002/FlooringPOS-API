package ukhalid.dev.flooringpos_api.controllers;

import org.springframework.web.bind.annotation.*;
import ukhalid.dev.flooringpos_api.entities.Brand;
import ukhalid.dev.flooringpos_api.services.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public Iterable<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {
        return brandService.createBrand(brand);
    }

    @PutMapping("/{id}")
    public Brand updateBrand(@PathVariable Integer id, @RequestBody Brand brand) {
        return brandService.updateBrand(id, brand);
    }

    @DeleteMapping("/{id}")
    public Brand deleteBrand(@PathVariable Integer id) {
        return brandService.deleteBrand(id);
    }

}
