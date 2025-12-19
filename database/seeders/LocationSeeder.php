<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class LocationSeeder extends Seeder
{
    public function run()
    {
        // 1. Insert Provinces
        $provinces = [
            ['id' => 11, 'name' => 'ACEH'],
            ['id' => 12, 'name' => 'SUMATERA UTARA'],
            ['id' => 13, 'name' => 'SUMATERA BARAT'],
            ['id' => 14, 'name' => 'RIAU'],
            ['id' => 15, 'name' => 'JAMBI'],
            ['id' => 16, 'name' => 'SUMATERA SELATAN'],
            ['id' => 17, 'name' => 'BENGKULU'],
            ['id' => 18, 'name' => 'LAMPUNG'],
            ['id' => 19, 'name' => 'KEPULAUAN BANGKA BELITUNG'],
            ['id' => 21, 'name' => 'KEPULAUAN RIAU'],
            ['id' => 31, 'name' => 'DKI JAKARTA'],
            ['id' => 32, 'name' => 'JAWA BARAT'],
            ['id' => 33, 'name' => 'JAWA TENGAH'],
            ['id' => 34, 'name' => 'DI YOGYAKARTA'],
            ['id' => 35, 'name' => 'JAWA TIMUR'],
            ['id' => 36, 'name' => 'BANTEN'],
            ['id' => 51, 'name' => 'BALI'],
            ['id' => 52, 'name' => 'NUSA TENGGARA BARAT'],
            ['id' => 53, 'name' => 'NUSA TENGGARA TIMUR'],
            ['id' => 61, 'name' => 'KALIMANTAN BARAT'],
            ['id' => 62, 'name' => 'KALIMANTAN TENGAH'],
            ['id' => 63, 'name' => 'KALIMANTAN SELATAN'],
            ['id' => 64, 'name' => 'KALIMANTAN TIMUR'],
            ['id' => 65, 'name' => 'KALIMANTAN UTARA'],
            ['id' => 71, 'name' => 'SULAWESI UTARA'],
            ['id' => 72, 'name' => 'SULAWESI TENGAH'],
            ['id' => 73, 'name' => 'SULAWESI SELATAN'],
            ['id' => 74, 'name' => 'SULAWESI TENGGARA'],
            ['id' => 75, 'name' => 'GORONTALO'],
            ['id' => 76, 'name' => 'SULAWESI BARAT'],
            ['id' => 81, 'name' => 'MALUKU'],
            ['id' => 82, 'name' => 'MALUKU UTARA'],
            ['id' => 91, 'name' => 'PAPUA BARAT'],
            ['id' => 92, 'name' => 'PAPUA'],
            ['id' => 93, 'name' => 'PAPUA SELATAN'],
            ['id' => 94, 'name' => 'PAPUA TENGAH'],
            ['id' => 95, 'name' => 'PAPUA PEGUNUNGAN'],
            ['id' => 96, 'name' => 'PAPUA BARAT DAYA'],
        ];

        DB::table('provinces')->insert($provinces);

        // 2. Insert Sample Regencies (Cities/Districts)
        // We link these to the Province ID above.
        $regencies = [
            // DKI Jakarta (ID 31)
            ['province_id' => 31, 'name' => 'KOTA JAKARTA PUSAT'],
            ['province_id' => 31, 'name' => 'KOTA JAKARTA SELATAN'],
            ['province_id' => 31, 'name' => 'KOTA JAKARTA BARAT'],
            ['province_id' => 31, 'name' => 'KOTA JAKARTA TIMUR'],
            
            // Jawa Barat (ID 32)
            ['province_id' => 32, 'name' => 'KOTA BANDUNG'],
            ['province_id' => 32, 'name' => 'KABUPATEN BANDUNG'],
            ['province_id' => 32, 'name' => 'KOTA BEKASI'],
            ['province_id' => 32, 'name' => 'KABUPATEN BOGOR'],

            // Jawa Tengah (ID 33)
            ['province_id' => 33, 'name' => 'KOTA SEMARANG'],
            ['province_id' => 33, 'name' => 'KOTA SURAKARTA'],

            // Jawa Timur (ID 35)
            ['province_id' => 35, 'name' => 'KOTA SURABAYA'],
            ['province_id' => 35, 'name' => 'KOTA MALANG'],

            // Bali (ID 51)
            ['province_id' => 51, 'name' => 'KOTA DENPASAR'],
            ['province_id' => 51, 'name' => 'KABUPATEN BADUNG'],
        ];

        DB::table('regencies')->insert($regencies);
    }
}