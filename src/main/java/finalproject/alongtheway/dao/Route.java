package finalproject.alongtheway.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "start")
	private String location1;

	@Column(name = "end")
	private String location2;

	@Column(name = "wp1_id")
	private String id1;

	@Column(name = "wp1_lat")
	private Double lat1;

	@Column(name = "wp1_long")
	private Double long1;

	@Column(name = "wp2_id")
	private String id2;

	@Column(name = "wp2_lat")
	private Double lat2;

	@Column(name = "wp3_long")
	private Double long2;

//	@Column(name = "wp4_id")
//	private String id4;
//
//	@Column(name = "wp4_lat")
//	private Double lat4;
//
//	@Column(name = "wp4_long")
//	private Double long4;
//
//	@Column(name = "wp5_id")
//	private String id5;
//
//	@Column(name = "wp5_lat")
//	private Double lat5;
//
//	@Column(name = "wp5_long")
//	private Double long5;

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public Double getLat1() {
		return lat1;
	}

	public void setLat1(Double lat1) {
		this.lat1 = lat1;
	}

	public Double getLong1() {
		return long1;
	}

	public void setLong1(Double long1) {
		this.long1 = long1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public Double getLat2() {
		return lat2;
	}

	public void setLat2(Double lat2) {
		this.lat2 = lat2;
	}

	public Double getLong2() {
		return long2;
	}

	public void setLong2(Double long2) {
		this.long2 = long2;
	}

//	public String getId4() {
//		return id4;
//	}
//
//	public void setId4(String id4) {
//		this.id4 = id4;
//	}
//
//	public Double getLat4() {
//		return lat4;
//	}
//
//	public void setLat4(Double lat4) {
//		this.lat4 = lat4;
//	}
//
//	public Double getLong4() {
//		return long4;
//	}
//
//	public void setLong4(Double long4) {
//		this.long4 = long4;
//	}
//
//	public String getId5() {
//		return id5;
//	}
//
//	public void setId5(String id5) {
//		this.id5 = id5;
//	}
//
//	public Double getLat5() {
//		return lat5;
//	}
//
//	public void setLat5(Double lat5) {
//		this.lat5 = lat5;
//	}
//
//	public Double getLong5() {
//		return long5;
//	}
//
//	public void setLong5(Double long5) {
//		this.long5 = long5;
//	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
