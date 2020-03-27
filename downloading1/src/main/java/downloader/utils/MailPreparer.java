package downloader.utils;

import downloader.models.FileInfo;
import downloader.models.Mail;

public interface MailPreparer<T> {
    Mail createMail(T t);
}
