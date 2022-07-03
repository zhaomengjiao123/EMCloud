//定义工具函数
function formatToTime(time) {
  var date = new Date(time * 1000); //time为s,转化成ms
  const year = date.getFullYear();
  const month = (date.getMonth()+1).toString().padStart(2,'0');
  const day = date.getDate().toString().padStart(2,'0');
  const hour = date.getHours().toString().padStart(2,'0');
  const minute = date.getMinutes().toString().padStart(2,'0');
  const second = date.getSeconds().toString().padStart(2,'0');

  return `${year}-${month}-${day} ${hour}:${minute}:${second}`
}
function formatToDate(time) {
  var date = new Date(time * 1000); //time为s,转化成ms
  const year = date.getFullYear();
  const month = (date.getMonth()+1).toString().padStart(2,'0');
  const day = date.getDate().toString().padStart(2,'0');

  return `${year}-${month}-${day}`
}

/**
<<<<<<< HEAD
 * @param {Date} time 需要转换的时间
 * @param {String} fmt 需要转换的格式 如 yyyy-MM-dd、yyyy-MM-dd HH:mm:ss
 */
export function formatTime(time, fmt) {
  if (!time) return '';
  else {
    const date = new Date(time);
    const o = {
      'M+': date.getMonth() + 1,
      'd+': date.getDate(),
      'H+': date.getHours(),
      'm+': date.getMinutes(),
      's+': date.getSeconds(),
      'q+': Math.floor((date.getMonth() + 3) / 3),
      S: date.getMilliseconds(),
    };
    if (/(y+)/.test(fmt))
      fmt = fmt.replace(
        RegExp.$1,
        (date.getFullYear() + '').substr(4 - RegExp.$1.length)
      );
    for (const k in o) {
      if (new RegExp('(' + k + ')').test(fmt)) {
        fmt = fmt.replace(
          RegExp.$1,
          RegExp.$1.length === 1
            ? o[k]
            : ('00' + o[k]).substr(('' + o[k]).length)
        );
      }
    }
    return fmt;
  }
}

export default {
  formatToTime,
  formatToDate
=======
 * 随机生成三位数
 * @returns {number}
 */
function mathRandom1000 (){
  return (Math.random() * 1000).toFixed(0) * 1
}


export default {
  formatToTime,
  formatToDate,
  mathRandom1000
>>>>>>> b8a4ca62808d71934c6fae2f8a46e691c26817b4
}
