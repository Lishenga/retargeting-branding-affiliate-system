CREATE DEFINER=`root`@`localhost` PROCEDURE `getAdverts`(IN deviceType VARCHAR(100), IN country VARCHAR(100),numberOfAds int, identity varchar(10), id long)
BEGIN
SELECT advert.id AS id, advert.title AS title, advert.description AS description, advert.media_url AS mediaUrl, advert.advert_mode AS advertMode, advert.advert_status AS advertStatus,
advert.expiration_date AS expirationDate, advert.is_published AS isPublished, advert.max_advertiser_limit AS maxAdvertiserLimit, advert.redirect_link + identity + id AS redirectLink,
advertbehavior.display_window_start_time AS displayWindowStartTime, advertbehavior.display_window_stop_time AS displayWindowStopTime, tags.name AS name, advert.created_at AS createdAt, advert.updated_at AS updatedAt FROM advert
INNER JOIN(
SELECT * from advertbehavior where device_type = deviceType
)AS advertbehavior ON advertbehavior.advert_id = advert.id AND advertbehavior.country = country
INNER JOIN adverttype ON advert.advert_type_id = adverttype.id
INNER JOIN(
SELECT * from tags where tags_status = 'ACTIVE'
)AS tags ON advert.id = tags.advert_id
WHERE advert.advert_status='ACTIVE' AND advert.is_deleted = FALSE AND advert.expiration_data >= CURDATE() 
ORDER BY cast(adverttype.priority as unsigned) DESC
LIMIT numberOfAds;
END