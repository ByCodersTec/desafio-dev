<?php

namespace Tests\Feature;

use Illuminate\Http\Testing\File;
use Illuminate\Support\Facades\Storage;
use Illuminate\Testing\Fluent\AssertableJson;
use Tests\TestCase;

class UploadFileTest extends TestCase
{
	/** @test  */
	public function cnab_file_can_be_uploaded_test()
	{

		$storageFile = tmpfile();
		$file = new File("CNAB.txt", $storageFile);

		$response = $this->postJson('/api/upload/file', [
			'cnab_file' => $file,
		]);

		$response->assertJsonStructure([
			"*" => [
				"type_id",
				"transaction_date",
				"transaction_value",
				"person_cpf",
				"transaction_card",
				"transaction_hour",
				"person_name",
				"legal_person_name"
			]
		]);
		$response->assertSuccessful();
		$response->assertStatus(200);
	}
}
