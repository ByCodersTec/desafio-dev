<?php

namespace App\Http\Services\Utils;

class RemoveAccentsService
{

	public static function execute(string $string)
	{
		return preg_replace(
			array(
				'/\xc3[\x80-\x85]/',
				'/\xc3\x87/',
				'/\xc3[\x88-\x8b]/',
				'/\xc3[\x8c-\x8f]/',
				'/\xc3([\x92-\x96]|\x98)/',
				'/\xc3[\x99-\x9c]/',

				'/\xc3[\xa0-\xa5]/',
				'/\xc3\xa7/',
				'/\xc3[\xa8-\xab]/',
				'/\xc3[\xac-\xaf]/',
				'/\xc3([\xb2-\xb6]|\xb8)/',
				'/\xc3[\xb9-\xbc]/',
			),
			str_split('ACEIOUaceiou', 1),
			$string
		);
	}
}
