package controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import bean.GoodsBean;
import component.SystemUserStore;
import entity.Goods;
import entity.SystemUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.GoodsRepository;
import repository.SystemUserRepository;

@RestController
public class goodsController {

  @Autowired
  private SystemUserRepository systemUserRepository;

  @Autowired
  private GoodsRepository goodsRepository;

  @Autowired
  private SystemUserStore systemUserStore;


  @SuppressWarnings({"finally", "rawtypes"})
  @GetMapping("/goods")
  public List<GoodsBean> getAllGoods() throws JsonProcessingException {
    List<GoodsBean> result = new ArrayList<>();
    try {
      List<Goods> goodsList = goodsRepository.findAll();
      for (Goods goods : goodsList) {
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setId(goods.getId());
        goodsBean.setName(goods.getName());
        result.add(goodsBean);
      }
      return result;
    } catch (Exception e) {

      GoodsBean error = new GoodsBean();
      error.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      error.setMessage("系統錯誤");
      result.add(error);
    } finally {
      return result;
    }

  }

  @SuppressWarnings("finally")
  @GetMapping("/goods/{id}")
  public GoodsBean getAllGoods(@PathVariable UUID id) {

    GoodsBean goodsBean = new GoodsBean();
    try {
      Optional<Goods> goods = goodsRepository.findById(id);
      if (goods.isPresent()) {
        Goods oldGoods = goods.get();
        goodsBean.setId(oldGoods.getId());
        goodsBean.setName(oldGoods.getName());
      }
      return goodsBean;
    } catch (Exception e) {
      goodsBean.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      goodsBean.setMessage("系統錯誤");
    } finally {
      return goodsBean;
    }
  }


  @SuppressWarnings({"finally", "static-access"})
  @PostMapping("/goods/add")
  public GoodsBean add(@RequestBody Map<String, Object> goodsNane, HttpServletRequest request) {
    GoodsBean goodsBean = new GoodsBean();
    try {
      Goods go = new Goods();
      String name = (String) goodsNane.get("goods_name");
      go.setName(name);
      // 從SystemUser 呼叫api使用者取得資訊
      String authHeader = request.getHeader("Authorization");
      String accessToken = authHeader.replace("Bearer", "").replace(":", "");
      UUID id = systemUserStore.getIdByToken(accessToken);
      List<UUID> ids = new ArrayList<>();
      ids.add(id);
      List<SystemUser> s = systemUserRepository.findAllById(ids);
      go.setCrUser(s.get(0));
      go.setCrDatetime(Timestamp.from(Instant.now()));
      go.setUpUser(s.get(0));
      go.setUpDatetime(Timestamp.from(Instant.now()));
      goodsRepository.save(go);
      goodsBean.setId(go.getId());
      goodsBean.setName(go.getName());
    } catch (Exception e) {
      goodsBean.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      goodsBean.setMessage("系統錯誤");
    } finally {
      return goodsBean;
    }
  }

  @SuppressWarnings("finally")
  @PutMapping("/goods/{id}")
  public List<GoodsBean> update(@PathVariable UUID id, @RequestBody Map<String, Object> goodsNane) {
    List<GoodsBean> result = new ArrayList<>();
    try {
      String name = (String) goodsNane.get("goods_name");
      List<UUID> wd = new ArrayList<>();
      wd.add(id);
      List<Goods> goodsList = goodsRepository.findAllById(wd);
      goodsList.stream().forEach(obj -> {
        obj.setId(id);
        obj.setCrDatetime(obj.getCrDatetime() == null ? null : obj.getCrDatetime());
        obj.setCrUser(obj.getCrUser() == null ? null : obj.getCrUser());
        obj.setUpDatetime(obj.getUpDatetime() == null ? null : obj.getUpDatetime());
        obj.setUpUser(obj.getUpUser() == null ? null : obj.getUpUser());
        obj.setName(name);
        goodsRepository.save(obj);
      });
      for (Goods goods : goodsList) {
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setId(goods.getId());
        goodsBean.setName(goods.getName());
        result.add(goodsBean);
      }
      return result;
    } catch (Exception e) {
      GoodsBean error = new GoodsBean();
      error.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      error.setMessage("系統錯誤");
      result.add(error);
    } finally {
      return result;
    }

  }

  @SuppressWarnings("finally")
  @DeleteMapping("/goods/{id}")
  public GoodsBean delete(@PathVariable UUID id) {
    GoodsBean goodsBean = new GoodsBean();
    try {
      List<UUID> wd = new ArrayList<>();
      wd.add(id);
      goodsRepository.deleteAllById(wd);
      goodsBean.setStatus(HttpServletResponse.SC_OK);
      goodsBean.setMessage("OK");
    } catch (Exception e) {
      goodsBean.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      goodsBean.setMessage("系統錯誤");
    } finally {
      return goodsBean;
    }
  }
}
