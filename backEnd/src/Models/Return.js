export default class Return
{
	constructor(statusCode = 200, message = 'OK', content = {} || [])
	{
		this.statusCode = statusCode;
		this.message = message;
		this.content = content || [];
	}
}
