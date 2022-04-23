import { Controller, Get } from '@nestjs/common';
import { StoresService } from 'src/services/stores/stores.service';

@Controller('stores')
export class StoresController {
    constructor(private readonly storesService: StoresService) {}

    @Get('/')
    async getStores() {
        return this.storesService.stores()
    }
}
