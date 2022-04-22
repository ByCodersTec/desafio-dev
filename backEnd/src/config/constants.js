import moment from 'moment';

export const SET_DATE_FORMAT = function (val)
{
  return moment(val || new Date())
    .utcOffset(process.env.TIMEZONE)
    .format('YYYY-MM-DDTHH:mm:ss.SSSZ');
}

export const GET_DATE_FORMAT = function (model, col)
{
  return moment(model.getDataValue(col))
    .utcOffset(process.env.TIMEZONE)
    .format('YYYY-MM-DDTHH:mm:ss.SSSZ');
}
