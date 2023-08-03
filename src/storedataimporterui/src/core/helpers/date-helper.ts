import moment from "moment";

export default class DateHelper {
    public static format(date: string | Date, format: string = "DD/MM/YYYY HH:mm:ss") {
        return moment(date).format(format)
    }
}